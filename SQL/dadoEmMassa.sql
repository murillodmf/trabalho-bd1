-- Inserção de dados para a tabela 'professor'
INSERT INTO professor (registro, p_nome, s_nome, CPF, idade) VALUES
(1001, 'Ana', 'Oliveira', '11122233344', 42), -- Professora de Português
(1002, 'Bruno', 'Martins', '22233344455', 38), -- Professor de Matemática
(1003, 'Carla', 'Santos', '33344455566', 48), -- Professora de História
(1004, 'Diego', 'Ferreira', '44455566677', 35); -- Professor de Ciências

-- Inserção de dados para a tabela 'aluno'
INSERT INTO aluno (matricula, cpf, pnome, snome, idade) VALUES
(2001, '99988877766', 'Lucas', 'Silva', 15),   -- Ensino Médio
(2002, '88877766655', 'Mariana', 'Costa', 16), -- Ensino Médio
(2003, '77766655544', 'Pedro', 'Lima', 11),   -- Ensino Fundamental II
(2004, '66655544433', 'Sofia', 'Almeida', 9),  -- Ensino Fundamental I
(2005, '55544433322', 'Gabriel', 'Pereira', 14); -- Ensino Médio

-- Inserção de dados para a tabela 'turma'
INSERT INTO turma (cod, materia, quantidadeAlunos, registro) VALUES
(301, 'Português - 9º Ano EF', 28, 1001), -- Professor Ana Oliveira
(302, 'Matemática - 1ª Série EM', 32, 1002), -- Professor Bruno Martins
(303, 'História - 8º Ano EF', 25, 1003), -- Professor Carla Santos
(304, 'Ciências - 5º Ano EF', 20, 1004), -- Professor Diego Ferreira
(305, 'Literatura - 2ª Série EM', 30, 1001); -- Professor Ana Oliveira

-- Inserção de dados para a tabela 'aluno_turma' (COMPÕE)
INSERT INTO aluno_turma (matricula, cod_turma) VALUES
(2001, 301), -- Lucas em Português - 9º Ano
(2001, 302), -- Lucas em Matemática - 1ª Série
(2002, 302), -- Mariana em Matemática - 1ª Série
(2002, 305), -- Mariana em Literatura - 2ª Série
(2003, 303), -- Pedro em História - 8º Ano
(2004, 304), -- Sofia em Ciências - 5º Ano
(2005, 301), -- Gabriel em Português - 9º Ano
(2005, 302); -- Gabriel em Matemática - 1ª Série

-- Inserção de dados para a tabela 'avaliacao'
INSERT INTO avaliacao (id_prova, data, nota_maxima, cod_turma) VALUES
(4001, '2025-06-20', 10.00, 301), -- Prova de Português - 9º Ano
(4002, '2025-06-25', 10.00, 302), -- Prova de Matemática - 1ª Série EM
(4003, '2025-07-01', 5.00, 301),  -- Trabalho de Português - 9º Ano
(4004, '2025-07-05', 10.00, 304); -- Prova de Ciências - 5º Ano

-- Inserção de dados para a tabela 'questao'
INSERT INTO questao (id_questao, enunciado) VALUES
(5001, 'Assinale a alternativa correta sobre a concordância verbal.'), -- Objetiva - Português
(5002, 'Calcule a área de um triângulo com base 10cm e altura 5cm.'), -- Dissertativa - Matemática
(5003, 'Qual dos biomas abaixo possui maior biodiversidade?'), -- Objetiva - Ciências
(5004, 'Explique a importância da Revolução Industrial.'), -- Dissertativa - História
(5005, 'Identifique o sujeito da oração: "Os alunos estudam muito."'); -- Objetiva - Português

-- Inserção de dados para a tabela 'avaliacao_contem_questao'
INSERT INTO avaliacao_contem_questao (id_prova, id_questao) VALUES
(4001, 5001), -- Prova Português tem Questão Concordância Verbal
(4001, 5005), -- Prova Português tem Questão Sujeito da Oração
(4003, 5001), -- Trabalho Português tem Questão Concordância Verbal (reuso)
(4002, 5002), -- Prova Matemática tem Questão Área do Triângulo
(4004, 5003); -- Prova Ciências tem Questão Biomas

-- Inserção de dados para a tabela 'objetiva'
INSERT INTO objetiva (id_questao, resposta) VALUES
(5001, 'O verbo concorda com o sujeito em número e pessoa.'),
(5003, 'Floresta Amazônica'),
(5005, 'Os alunos');

-- Inserção de dados para a tabela 'objetiva_resposta'
INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES
(5001, 'O verbo concorda com o sujeito em número e pessoa.'),
(5001, 'O verbo concorda com o objeto direto.'),
(5001, 'O verbo é sempre invariável.'),
(5003, 'Cerrado'),
(5003, 'Floresta Amazônica'),
(5003, 'Caatinga'),
(5005, 'Os alunos'),
(5005, 'estudam'),
(5005, 'muito');

-- Inserção de dados para a tabela 'dissertativa'
INSERT INTO dissertativa (id_questao, respostaModelo, resposta) VALUES
(5002, 'A área do triângulo é calculada por (base * altura) / 2. Assim, (10 * 5) / 2 = 25 cm².', NULL),
(5004, 'A Revolução Industrial foi um período de grandes transformações econômicas e sociais que marcou a transição da produção artesanal para a industrial, com uso de máquinas e fontes de energia como o vapor. Isso levou ao surgimento de novas classes sociais, urbanização e mudanças nas relações de trabalho.', NULL);

-- Inserção de dados para a tabela 'realizaProva'
INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES
-- Prova 4001 (Português - 9º Ano)
(4001, 5001, 2001, 9.00, 'Boa resposta, mas faltou aprofundar.'),
(4001, 5005, 2001, 10.00, 'Identificação correta.'),
(4001, 5001, 2005, 8.00, 'Acertou, mas com dúvidas.'),
(4001, 5005, 2005, 9.00, 'Muito bem.'),

-- Prova 4002 (Matemática - 1ª Série EM)
(4002, 5002, 2001, 7.50, 'Faltou a unidade de medida.'),
(4002, 5002, 2002, 9.00, 'Cálculo correto.'),

-- Trabalho 4003 (Português - 9º Ano)
(4003, 5001, 2001, 5.00, 'Acertou o item, mas não justificou.'),

-- Prova 4004 (Ciências - 5º Ano EF)
(4004, 5003, 2004, 10.00, 'Muito bem, Sofia!');