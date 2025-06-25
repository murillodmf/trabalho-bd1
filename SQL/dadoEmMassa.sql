-- Dados para a tabela professor
INSERT INTO professor (registro, nome, CPF) VALUES
                                                (1001, 'Dr. Ana Paula Silva', '11122233301'),
                                                (1002, 'Prof. Carlos Eduardo Santos', '44455566602'),
                                                (1003, 'Dra. Maria Fernanda Oliveira', '77788899903'),
                                                (1004, 'Prof. João Pedro Costa', '12345678904');

-- Dados para a tabela aluno
INSERT INTO aluno (matricula, cpf, pnome, snome, idade) VALUES
                                                            (20001, '98765432101', 'Luiza', 'Martins', 20),
                                                            (20002, '12312312302', 'Felipe', 'Rocha', 21),
                                                            (20003, '32132132103', 'Isabela', 'Gonçalves', 19),
                                                            (20004, '45645645604', 'Bruno', 'Lima', 22),
                                                            (20005, '78978978905', 'Mariana', 'Almeida', 20),
                                                            (20006, '11111111106', 'Gustavo', 'Pereira', 21),
                                                            (20007, '22222222207', 'Sofia', 'Rodrigues', 19);

-- Dados para a tabela turma
INSERT INTO turma (cod, materia, quantidadeAlunos, registro) VALUES
                                                                 (301, 'Banco de Dados', 30, 1001),
                                                                 (302, 'Estrutura de Dados', 25, 1002),
                                                                 (303, 'Programação Orientada a Objetos', 35, 1003),
                                                                 (304, 'Cálculo I', 40, 1004),
                                                                 (305, 'Sistemas Operacionais', 28, 1001);

-- Dados para a tabela avaliacao
INSERT INTO avaliacao (id_prova, data, nota_maxima) VALUES
                                                        (4001, '2024-05-10', 10.00),
                                                        (4002, '2024-06-01', 10.00),
                                                        (4003, '2024-06-15', 8.00),
                                                        (4004, '2024-06-20', 7.50);

-- Dados para a tabela questao
INSERT INTO questao (id_questao, enunciado) VALUES
                                                (5001, 'Qual a função principal de uma chave primária em um banco de dados?'),
                                                (5002, 'Descreva a diferença entre DDL e DML em SQL.'),
                                                (5003, 'Qual tipo de JOIN retorna todas as linhas de ambas as tabelas, com NULLs onde não há correspondência?'),
                                                (5004, 'Explique o conceito de normalização em bancos de dados e seus objetivos.'),
                                                (5005, 'O que significa ACID em transações de banco de dados?'),
                                                (5006, 'Considere a tabela "Funcionarios" com colunas "id" e "nome". Qual comando SQL é usado para adicionar uma nova coluna "salario" tipo DECIMAL(10,2)?');

-- Dados para a tabela avaliacao_contem_questao
INSERT INTO avaliacao_contem_questao (id_prova, id_questao) VALUES
                                                                (4001, 5001), -- Prova 4001 contém Questao 5001
                                                                (4001, 5002), -- Prova 4001 contém Questao 5002
                                                                (4001, 5003), -- Prova 4001 contém Questao 5003
                                                                (4002, 5004), -- Prova 4002 contém Questao 5004
                                                                (4002, 5005), -- Prova 4002 contém Questao 5005
                                                                (4003, 5001),
                                                                (4003, 5006),
                                                                (4004, 5002);

-- Dados para a tabela objetiva (ligadas a questao)
INSERT INTO objetiva (id_questao, resposta) VALUES
                                                (5001, 'Garantir a unicidade e integridade dos registros'),
                                                (5003, 'FULL OUTER JOIN'),
                                                (5005, 'Atomicity, Consistency, Isolation, Durability'),
                                                (5006, 'ALTER TABLE Funcionarios ADD COLUMN salario DECIMAL(10,2);');


-- Dados para a tabela objetiva_resposta (alternativas para as questões objetivas)
INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES
                                                            (5001, 'Garantir a unicidade e integridade dos registros'),
                                                            (5001, 'Aumentar a velocidade das consultas'),
                                                            (5001, 'Armazenar dados duplicados'),
                                                            (5001, 'Facilitar a exclusão de dados'),
                                                            (5003, 'INNER JOIN'),
                                                            (5003, 'LEFT JOIN'),
                                                            (5003, 'RIGHT JOIN'),
                                                            (5003, 'FULL OUTER JOIN'),
                                                            (5005, 'Availability, Consistency, Integrity, Data'),
                                                            (5005, 'Atomicity, Consistency, Isolation, Durability'),
                                                            (5005, 'Access, Control, Integrity, Data'),
                                                            (5006, 'ALTER TABLE Funcionarios ADD COLUMN salario DECIMAL(10,2);'),
                                                            (5006, 'ADD COLUMN salario DECIMAL(10,2) TO Funcionarios;'),
                                                            (5006, 'UPDATE Funcionarios SET salario DECIMAL(10,2);');


-- Dados para a tabela dissertativa (ligadas a questao)
INSERT INTO dissertativa (id_questao, respostaModelo, resposta) VALUES
                                                                    (5002, 'DDL (Data Definition Language) é usada para definir e gerenciar a estrutura do banco de dados (ex: CREATE, ALTER, DROP). DML (Data Manipulation Language) é usada para manipular os dados dentro das tabelas (ex: INSERT, UPDATE, DELETE, SELECT).', NULL),
                                                                    (5004, 'Normalização é o processo de organizar os dados em um banco de dados para minimizar a redundância e melhorar a integridade dos dados. Os objetivos incluem eliminar anomalias de inserção, atualização e exclusão, e garantir a consistência dos dados.', NULL);

-- Dados para a tabela realizaProva
INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES
-- Prova 4001 - Luiza Martins (20001)
(4001, 5001, 20001, 9.50, 'Resposta clara e precisa.'),
(4001, 5002, 20001, 8.00, 'Boa explicação, mas faltou um exemplo.'),
(4001, 5003, 20001, 10.00, 'Correto!'),
-- Prova 4001 - Felipe Rocha (20002)
(4001, 5001, 20002, 7.00, 'Compreensão básica.'),
(4001, 5002, 20002, 6.50, 'Necessita de mais detalhes.'),
(4001, 5003, 20002, 9.00, 'Quase perfeito.'),
-- Prova 4002 - Isabela Gonçalves (20003)
(4002, 5004, 20003, 9.00, 'Compreensão completa do conceito.'),
(4002, 5005, 20003, 8.50, 'Bem explicado.'),
-- Prova 4003 - Bruno Lima (20004)
(4003, 5001, 20004, 8.00, 'Resposta aceitável.'),
(4003, 5006, 20004, 7.50, 'Sintaxe correta.'),
-- Prova 4004 - Mariana Almeida (20005)
(4004, 5002, 20005, 9.00, 'Exemplos muito bons.'),
-- Prova 4001 - Gustavo Pereira (20006)
(4001, 5001, 20006, 8.50, 'Bom trabalho.'),
(4001, 5002, 20006, 7.00, 'Poderia ter mais profundidade.'),
(4001, 5003, 20006, 9.50, 'Excelente!');