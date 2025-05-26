CREATE TABLE professor (
    registro INT PRIMARY KEY,
    nome VARCHAR(100),
    CPF CHAR(11)
);

CREATE TABLE aluno (
    matricula INT PRIMARY KEY,
    cpf CHAR(11),
    pnome VARCHAR(50),
    snome VARCHAR(50),
    idade INT
);

CREATE TABLE turma (
    cod INT PRIMARY KEY,
    materia VARCHAR(100),
    quantidadeAlunos INT,
    registro INT,
    FOREIGN KEY (registro) REFERENCES professor(registro)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE avaliacao (
    id_prova INT PRIMARY KEY,
    data DATE,
    nota_maxima DECIMAL(5,2)
);

CREATE TABLE questao (
    id_questao INT PRIMARY KEY,
    enunciado TEXT
);

CREATE TABLE avaliacao_contem_questao (
    id_prova INT,
    id_questao INT,
    PRIMARY KEY (id_prova, id_questao),
    FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE objetiva (
    id_questao INT PRIMARY KEY,
    resposta VARCHAR(255),
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE objetiva_resposta (
    id_questao INT,
    alternativa VARCHAR(255),
    PRIMARY KEY (id_questao, alternativa),
    FOREIGN KEY (id_questao) REFERENCES objetiva(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE dissertativa (
    id_questao INT PRIMARY KEY,
    respostaModelo TEXT,
    resposta TEXT,
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE realizaProva (
    id_prova INT,
    id_questao INT,
    matricula INT,
    nota DECIMAL(5,2),
    comentario TEXT,
    PRIMARY KEY (id_prova, id_questao, matricula),
    FOREIGN KEY (id_prova) REFERENCES avaliacao(id_prova)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (matricula) REFERENCES aluno(matricula)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
