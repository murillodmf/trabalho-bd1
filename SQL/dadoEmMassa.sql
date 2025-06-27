-- Inserção de dados para a tabela professor
INSERT INTO professor (registro, p_nome, s_nome, CPF, idade) VALUES
(1001, 'Ana', 'Silva', '11122233344', 35),
(1002, 'Bruno', 'Pereira', '22233344455', 42),
(1003, 'Carla', 'Oliveira', '33344455566', 29),
(1004, 'Daniel', 'Santos', '44455566677', 50),
(1005, 'Eliana', 'Costa', '55566677788', 38);

-- Inserção de dados para a tabela aluno
INSERT INTO aluno (matricula, cpf, pnome, snome, idade) VALUES
(2001, '99988877766', 'Maria', 'Souza', 10), -- Fundamental
(2002, '88877766655', 'João', 'Lima', 11),   -- Fundamental
(2003, '77766655544', 'Pedro', 'Gomes', 15),  -- Médio
(2004, '66655544433', 'Ana', 'Ribeiro', 9),   -- Fundamental
(2005, '55544433322', 'Lucas', 'Martins', 16), -- Médio
(2006, '44433322211', 'Mariana', 'Almeida', 12),-- Fundamental
(2007, '33322211100', 'Fernando', 'Nunes', 17),-- Médio
(2008, '22211100099', 'Laura', 'Ferreira', 8),  -- Fundamental
(2009, '11100099988', 'Gabriel', 'Castro', 14), -- Médio
(2010, '00099988877', 'Sofia', 'Dias', 7);    -- Fundamental

-- Inserção de dados para a tabela turma
-- Professores: Ana (1001), Bruno (1002), Carla (1003), Daniel (1004), Eliana (1005)
INSERT INTO turma (cod, materia, quantidadeAlunos, registro) VALUES
(3001, 'Matemática (Ensino Fundamental)', 2, 1001), -- Prof. Ana
(3002, 'Português (Ensino Fundamental)', 2, 1001), -- Prof. Ana
(3003, 'História (Ensino Médio)', 2, 1002),       -- Prof. Bruno
(3004, 'Ciências (Ensino Fundamental)', 2, 1003),    -- Prof. Carla
(3005, 'Geografia (Ensino Médio)', 2, 1004);      -- Prof. Daniel

-- Inserção de dados para a tabela avaliacao
-- cod_turma: 3001 (Mat. EF), 3002 (Port. EF), 3003 (Hist. EM), 3004 (Cienc. EF), 3005 (Geo. EM)
INSERT INTO avaliacao (id_prova, data, nota_maxima, cod_turma) VALUES
(4001, '2024-05-10', 10.00, 3001), -- Matemática Fundamental - Prova 1
(4002, '2024-05-15', 10.00, 3002), -- Português Fundamental - Prova 1
(4003, '2024-05-20', 10.00, 3003), -- História Ensino Médio - Prova 1
(4004, '2024-06-01', 10.00, 3001), -- Matemática Fundamental - Prova 2
(4005, '2024-06-05', 10.00, 3004), -- Ciências Fundamental - Prova 1
(4006, '2024-06-10', 10.00, 3005); -- Geografia Ensino Médio - Prova 1

-- Inserção de dados para a tabela questao
-- id_questao é SERIAL, então não precisamos especificar
INSERT INTO questao (enunciado) VALUES
('Qual é o resultado de 5 + 3?'), -- Q1 - Fund. Matemática (Obj)
('Escreva uma frase sobre o verão.'), -- Q2 - Fund. Português (Diss)
('Quem proclamou a Independência do Brasil?'), -- Q3 - Médio História (Obj)
('Descreva o ciclo da água.'), -- Q4 - Fund. Ciências (Diss)
('Qual a capital da França?'), -- Q5 - Médio Geografia (Obj)
('Calcule 15 - 7.'), -- Q6 - Fund. Matemática (Obj)
('Qual o sinônimo de "feliz"?'), -- Q7 - Fund. Português (Obj)
('Explique a Revolução Francesa.'), -- Q8 - Médio História (Diss)
('Quais são os estados da água?'), -- Q9 - Fund. Ciências (Obj)
('Cite três países da América do Sul.'); -- Q10 - Médio Geografia (Diss)

-- A instrução SERIAL para id_questao gerará IDs automaticamente.
-- Para o propósito deste script de inserção em massa, vamos assumir que os IDs gerados são sequenciais a partir de 1.
-- Se você estiver usando um ambiente SQL que não garanta isso, precisará ajustar as referências de ID.
-- Para simplicidade e compatibilidade, vou usar IDs fictícios que correspondem à ordem de inserção.
-- Assumindo id_questao gerado como 1, 2, 3, ... 10

-- Inserção de dados para a tabela avaliacao_contem_questao
-- Prova 4001 (Mat. EF) - Q1, Q6
-- Prova 4002 (Port. EF) - Q2, Q7
-- Prova 4003 (Hist. EM) - Q3, Q8
-- Prova 4004 (Mat. EF) - Q1, Q6 (reuso de questões)
-- Prova 4005 (Cienc. EF) - Q4, Q9
-- Prova 4006 (Geo. EM) - Q5, Q10
INSERT INTO avaliacao_contem_questao (id_prova, id_questao) VALUES
(4001, 1), (4001, 6),
(4002, 2), (4002, 7),
(4003, 3), (4003, 8),
(4004, 1), (4004, 6), -- Reutilizando questões
(4005, 4), (4005, 9),
(4006, 5), (4006, 10);

-- Inserção de dados para a tabela objetiva
-- Q1 (5+3?), Q3 (Indep. Brasil?), Q5 (Capital França?), Q6 (15-7?), Q7 (Sin. Feliz?), Q9 (Estados água?)
INSERT INTO objetiva (id_questao, resposta) VALUES
(1, '8'),
(3, 'Dom Pedro I'),
(5, 'Paris'),
(6, '8'),
(7, 'Alegre'),
(9, 'Sólido, líquido e gasoso');

-- Inserção de dados para a tabela objetiva_resposta
INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES
(1, '7'), (1, '8'), (1, '9'), (1, '10'),
(3, 'Tiradentes'), (3, 'Dom Pedro I'), (3, 'Princesa Isabel'), (3, 'Getúlio Vargas'),
(5, 'Londres'), (5, 'Roma'), (5, 'Madri'), (5, 'Paris'),
(6, '7'), (6, '8'), (6, '9'), (6, '10'),
(7, 'Triste'), (7, 'Bravo'), (7, 'Alegre'), (7, 'Cansado'),
(9, 'Sólido, líquido e gasoso'), (9, 'Água e vapor'), (9, 'Congelado e aquecido'), (9, 'Frio e quente');

-- Inserção de dados para a tabela dissertativa
-- Q2 (Frase verão?), Q4 (Ciclo água?), Q8 (Rev. Francesa?), Q10 (3 países AS?)
INSERT INTO dissertativa (id_questao, respostaModelo, resposta) VALUES
(2, 'O verão é uma estação quente e ensolarada, ideal para ir à praia.', NULL),
(4, 'O ciclo da água envolve evaporação, condensação, precipitação e coleta.', NULL),
(8, 'A Revolução Francesa foi um período de grandes mudanças sociais e políticas na França, marcada pela queda da monarquia e o surgimento da república.', NULL),
(10, 'Brasil, Argentina e Chile.', NULL);

-- Inserção de dados para a tabela aluno_turma
-- Alunos: Maria (2001), João (2002), Pedro (2003), Ana (2004), Lucas (2005), Mariana (2006), Fernando (2007), Laura (2008), Gabriel (2009), Sofia (2010)
-- Turmas: 3001 (Mat. EF), 3002 (Port. EF), 3003 (Hist. EM), 3004 (Cienc. EF), 3005 (Geo. EM)
INSERT INTO aluno_turma (matricula, cod_turma) VALUES
(2001, 3001), -- Maria em Matemática EF
(2002, 3001), -- João em Matemática EF
(2001, 3002), -- Maria em Português EF
(2004, 3002), -- Ana em Português EF
(2003, 3003), -- Pedro em História EM
(2005, 3003), -- Lucas em História EM
(2004, 3004), -- Ana em Ciências EF
(2008, 3004), -- Laura em Ciências EF
(2005, 3005), -- Lucas em Geografia EM
(2007, 3005); -- Fernando em Geografia EM

-- Inserção de dados para a tabela realizaProva
-- Nota do aluno para uma questão específica de uma prova
-- id_prova: 4001, 4002, 4003, 4004, 4005, 4006
-- id_questao: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
-- matricula: 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010
INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES
-- Prova 4001 (Mat. EF)
(4001, 1, 2001, 1.00, 'Acertou a soma.'),
(4001, 6, 2001, 1.00, 'Acertou a subtração.'),
(4001, 1, 2002, 0.50, 'Errou a soma.'),
(4001, 6, 2002, 1.00, 'Acertou a subtração.'),

-- Prova 4002 (Port. EF)
(4002, 2, 2001, 1.00, 'Frase bem elaborada.'),
(4002, 7, 2001, 1.00, 'Acertou o sinônimo.'),
(4002, 2, 2004, 0.75, 'Frase simples.'),
(4002, 7, 2004, 0.00, 'Errou o sinônimo.'),

-- Prova 4003 (Hist. EM)
(4003, 3, 2003, 1.00, 'Resposta correta.'),
(4003, 8, 2003, 1.00, 'Explicação completa.'),
(4003, 3, 2005, 1.00, 'Resposta correta.'),
(4003, 8, 2005, 0.80, 'Explicação com alguns pontos a melhorar.'),

-- Prova 4004 (Mat. EF - reuso da prova 4001, para mostrar reavaliação ou segunda chamada)
(4004, 1, 2001, 1.00, 'Acertou novamente.'),
(4004, 6, 2001, 1.00, 'Acertou novamente.'),
(4004, 1, 2002, 1.00, 'Melhorou na soma!'),
(4004, 6, 2002, 1.00, 'Manteve o acerto.'),

-- Prova 4005 (Cienc. EF)
(4005, 4, 2004, 0.90, 'Descreveu bem o ciclo.'),
(4005, 9, 2004, 1.00, 'Respondeu corretamente os estados.'),
(4005, 4, 2008, 0.60, 'Faltou detalhe na descrição.'),
(4005, 9, 2008, 1.00, 'Respondeu corretamente os estados.'),

-- Prova 4006 (Geo. EM)
(4006, 5, 2005, 1.00, 'Capital correta.'),
(4006, 10, 2005, 1.00, 'Citou três países corretamente.'),
(4006, 5, 2007, 1.00, 'Capital correta.'),
(4006, 10, 2007, 0.70, 'Citou apenas dois países.');