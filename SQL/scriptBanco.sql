CREATE TABLE professor (
    registro SERIAL PRIMARY KEY,
    p_nome VARCHAR(50) NOT NULL,
    s_nome VARCHAR(50),
    CPF CHAR(11) NOT NULL UNIQUE,
    idade INT CHECK (idade > 18)
);

CREATE TABLE aluno (
    matricula SERIAL PRIMARY KEY,
    cpf CHAR(11) NOT NULL UNIQUE,
    pnome VARCHAR(50) NOT NULL,
    snome VARCHAR(50),
    idade INT CHECK (idade > 0)
);

CREATE TABLE turma (
    cod SERIAL PRIMARY KEY,
    materia VARCHAR(100) NOT NULL,
    quantidadeAlunos INT DEFAULT 0,
    registro INT,
    CONSTRAINT fk_turma_professor FOREIGN KEY (registro) REFERENCES professor(registro)
        ON DELETE SET NULL
);

CREATE TABLE avaliacao (
    id_prova SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    nota_maxima DECIMAL(5,2) NOT NULL CHECK (nota_maxima > 0),
    cod_turma INT,
    CONSTRAINT fk_avaliacao_turma FOREIGN KEY (cod_turma) REFERENCES turma(cod)
        ON DELETE SET NULL
);

CREATE TABLE questao (
    id_questao SERIAL PRIMARY KEY,
    enunciado TEXT NOT NULL,
    tipo VARCHAR(20) NOT NULL DEFAULT 'OBJETIVA' CHECK (tipo IN ('OBJETIVA', 'DISSERTATIVA'))
);

CREATE TABLE avaliacao_contem_questao (
    id_prova INT,
    id_questao INT,
    CONSTRAINT pk_avaliacao_contem_questao PRIMARY KEY (id_prova, id_questao),
    CONSTRAINT fk_acq_avaliacao FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON DELETE CASCADE,
    CONSTRAINT fk_acq_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON DELETE CASCADE
);

CREATE TABLE objetiva (
    id_questao INT PRIMARY KEY,
    respostaCorreta VARCHAR(255) NOT NULL,
    CONSTRAINT fk_objetiva_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON DELETE CASCADE
);

CREATE TABLE objetiva_resposta (
    id_questao INT,
    alternativa VARCHAR(255) NOT NULL,
    CONSTRAINT pk_objetiva_resposta PRIMARY KEY (id_questao, alternativa),
    CONSTRAINT fk_or_objetiva FOREIGN KEY (id_questao) REFERENCES objetiva(id_questao)
        ON DELETE CASCADE
);

CREATE TABLE dissertativa (
    id_questao INT PRIMARY KEY,
    respostaModelo TEXT,
    CONSTRAINT fk_dissertativa_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON DELETE CASCADE
);

CREATE TABLE realizaProva (
    id_prova INT,
    id_questao INT,
    matricula INT,
    nota DECIMAL(5,2) CHECK (nota >= 0),
    comentario TEXT,
    CONSTRAINT pk_realizaProva PRIMARY KEY (id_prova, id_questao, matricula),
    CONSTRAINT fk_rp_avaliacao FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON DELETE CASCADE,
    CONSTRAINT fk_rp_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON DELETE CASCADE,
    CONSTRAINT fk_rp_aluno FOREIGN KEY (matricula) REFERENCES aluno(matricula)
        ON DELETE CASCADE
);

CREATE TABLE aluno_turma (
    matricula INT,
    cod_turma INT,
    CONSTRAINT pk_aluno_turma PRIMARY KEY (matricula, cod_turma),
    CONSTRAINT fk_at_aluno FOREIGN KEY (matricula) REFERENCES aluno(matricula)
        ON DELETE CASCADE,
    CONSTRAINT fk_at_turma FOREIGN KEY (cod_turma) REFERENCES turma(cod)
        ON DELETE CASCADE
);

CREATE INDEX idx_professor_cpf ON professor(CPF);
CREATE INDEX idx_aluno_cpf ON aluno(cpf);
CREATE INDEX idx_turma_professor ON turma(registro);
CREATE INDEX idx_avaliacao_turma ON avaliacao(cod_turma);
CREATE INDEX idx_realiza_prova_aluno ON realizaProva(matricula);

CREATE OR REPLACE FUNCTION atualiza_quantidade_alunos()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        UPDATE turma
        SET quantidadeAlunos = (SELECT COUNT(*) FROM aluno_turma WHERE cod_turma = OLD.cod_turma)
        WHERE cod = OLD.cod_turma;
    ELSIF (TG_OP = 'INSERT') THEN
        UPDATE turma
        SET quantidadeAlunos = (SELECT COUNT(*) FROM aluno_turma WHERE cod_turma = NEW.cod_turma)
        WHERE cod = NEW.cod_turma;
    ELSIF (TG_OP = 'UPDATE' AND OLD.cod_turma <> NEW.cod_turma) THEN
        UPDATE turma
        SET quantidadeAlunos = (SELECT COUNT(*) FROM aluno_turma WHERE cod_turma = OLD.cod_turma)
        WHERE cod = OLD.cod_turma;
        UPDATE turma
        SET quantidadeAlunos = (SELECT COUNT(*) FROM aluno_turma WHERE cod_turma = NEW.cod_turma)
        WHERE cod = NEW.cod_turma;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trig_atualiza_quantidade
AFTER INSERT OR DELETE OR UPDATE ON aluno_turma
FOR EACH ROW EXECUTE FUNCTION atualiza_quantidade_alunos();