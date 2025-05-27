CREATE TABLE professor (
    registro INT,
    nome VARCHAR(100) NOT NULL,
    CPF CHAR(11) NOT NULL,
    CONSTRAINT pk_professor PRIMARY KEY (registro)
);

CREATE TABLE aluno (
    matricula INT,
    cpf CHAR(11) NOT NULL,
    pnome VARCHAR(50) NOT NULL,
    snome VARCHAR(50),
    idade INT,
    CONSTRAINT pk_aluno PRIMARY KEY (matricula)
);

CREATE TABLE turma (
    cod INT,
    materia VARCHAR(100) NOT NULL,
    quantidadeAlunos INT,
    registro INT,
    CONSTRAINT pk_turma PRIMARY KEY (cod),
    CONSTRAINT fk_turma_professor FOREIGN KEY (registro) REFERENCES professor(registro)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE avaliacao (
    id_prova INT,
    data DATE NOT NULL,
    nota_maxima DECIMAL(5,2) NOT NULL,
    CONSTRAINT pk_avaliacao PRIMARY KEY (id_prova)
);

CREATE TABLE questao (
    id_questao INT,
    enunciado TEXT NOT NULL,
    CONSTRAINT pk_questao PRIMARY KEY (id_questao)
);

CREATE TABLE avaliacao_contem_questao (
    id_prova INT,
    id_questao INT,
    CONSTRAINT pk_avaliacao_contem_questao PRIMARY KEY (id_prova, id_questao),
    CONSTRAINT fk_acq_avaliacao FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_acq_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE objetiva (
    id_questao INT,
    resposta VARCHAR(255) NOT NULL,
    CONSTRAINT pk_objetiva PRIMARY KEY (id_questao),
    CONSTRAINT fk_objetiva_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE objetiva_resposta (
    id_questao INT,
    alternativa VARCHAR(255) NOT NULL,
    CONSTRAINT pk_objetiva_resposta PRIMARY KEY (id_questao, alternativa),
    CONSTRAINT fk_or_objetiva FOREIGN KEY (id_questao) REFERENCES objetiva(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE dissertativa (
    id_questao INT,
    respostaModelo TEXT,
    resposta TEXT,
    CONSTRAINT pk_dissertativa PRIMARY KEY (id_questao),
    CONSTRAINT fk_dissertativa_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE realizaProva (
    id_prova INT,
    id_questao INT,
    matricula INT,
    nota DECIMAL(5,2),
    comentario TEXT,
    CONSTRAINT pk_realizaProva PRIMARY KEY (id_prova, id_questao, matricula),
    CONSTRAINT fk_rp_avaliacao FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_rp_questao FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_rp_aluno FOREIGN KEY (matricula) REFERENCES aluno(matricula)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
