-- Professores (registro é SERIAL, não precisa informar)
INSERT INTO professor (p_nome, s_nome, CPF, idade) VALUES
('Carlos', 'Silva', '12345678901', 45),
('Maria', 'Oliveira', '22345678902', 38),
('João', 'Souza', '32345678903', 50),
('Fernanda', 'Alves', '42345678904', 42);

-- Alunos
INSERT INTO aluno (cpf, pnome, snome, idade) VALUES
('11111111111', 'Ana', 'Santos', 20),
('22222222222', 'Bruno', 'Lima', 21),
('33333333333', 'Carla', 'Oliveira', 19),
('44444444444', 'Daniel', 'Pereira', 23),
('55555555555', 'Eduarda', 'Costa', 22),
('66666666666', 'Felipe', 'Gomes', 24),
('77777777777', 'Giovana', 'Dias', 21),
('88888888888', 'Henrique', 'Barros', 20),
('99999999999', 'Isabela', 'Ramos', 19),
('00000000000', 'João', 'Machado', 22);

-- Turmas
-- Precisamos pegar o registro dos professores criados, assumindo IDs gerados
WITH profs AS (
    SELECT registro FROM professor ORDER BY registro LIMIT 4
)
INSERT INTO turma (materia, registro) VALUES
('Matemática', (SELECT registro FROM profs OFFSET 0 LIMIT 1)),
('História', (SELECT registro FROM profs OFFSET 1 LIMIT 1)),
('Biologia', (SELECT registro FROM profs OFFSET 2 LIMIT 1)),
('Geografia', (SELECT registro FROM profs OFFSET 3 LIMIT 1));

-- Alunos nas turmas
-- Pegamos matriculas e cod_turma dinamicamente
WITH
alunos AS (SELECT matricula FROM aluno ORDER BY matricula LIMIT 10),
turmas AS (SELECT cod FROM turma ORDER BY cod LIMIT 4)
INSERT INTO aluno_turma (matricula, cod_turma) VALUES
((SELECT matricula FROM alunos OFFSET 0 LIMIT 1), (SELECT cod FROM turmas OFFSET 0 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 1 LIMIT 1), (SELECT cod FROM turmas OFFSET 0 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 2 LIMIT 1), (SELECT cod FROM turmas OFFSET 0 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 3 LIMIT 1), (SELECT cod FROM turmas OFFSET 1 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 4 LIMIT 1), (SELECT cod FROM turmas OFFSET 1 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 5 LIMIT 1), (SELECT cod FROM turmas OFFSET 1 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 6 LIMIT 1), (SELECT cod FROM turmas OFFSET 2 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 7 LIMIT 1), (SELECT cod FROM turmas OFFSET 2 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 8 LIMIT 1), (SELECT cod FROM turmas OFFSET 2 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 9 LIMIT 1), (SELECT cod FROM turmas OFFSET 3 LIMIT 1)),
((SELECT matricula FROM alunos OFFSET 0 LIMIT 1), (SELECT cod FROM turmas OFFSET 3 LIMIT 1));

-- Avaliações (id_prova é SERIAL)
WITH turmas AS (SELECT cod FROM turma ORDER BY cod LIMIT 4)
INSERT INTO avaliacao (data, nota_maxima, cod_turma) VALUES
(CURRENT_DATE, 10.0, (SELECT cod FROM turmas OFFSET 0 LIMIT 1)),
(CURRENT_DATE, 10.0, (SELECT cod FROM turmas OFFSET 1 LIMIT 1)),
(CURRENT_DATE, 10.0, (SELECT cod FROM turmas OFFSET 2 LIMIT 1)),
(CURRENT_DATE, 10.0, (SELECT cod FROM turmas OFFSET 3 LIMIT 1));

-- Questões (id_questao SERIAL)
INSERT INTO questao (enunciado) VALUES
('Quanto é 2 + 2?'),
('Qual a capital do Brasil?'),
('Quantos dias há em um ano?'),
('Quem descobriu o Brasil?'),
('Cor primária que resulta do vermelho + azul?'),
('Qual é o maior planeta do sistema solar?'),
('Explique a Revolução Francesa'),
('Fale sobre a fotossíntese'),
('Explique as placas tectônicas'),
('Fale sobre o ciclo da água'),
('Explique o conceito de PIB'),
('Explique a teoria da evolução');

-- Objetivas (primeiras 6 questões)
INSERT INTO objetiva (id_questao, resposta)
SELECT id_questao, resposta FROM (
    VALUES
    (1, '4'),
    (2, 'Brasília'),
    (3, '365'),
    (4, 'Pedro Álvares Cabral'),
    (5, 'Roxo'),
    (6, 'Júpiter')
) AS t(id_questao, resposta);

INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES
(1, '3'), (1, '4'), (1, '5'),
(2, 'Rio de Janeiro'), (2, 'São Paulo'), (2, 'Brasília'),
(3, '360'), (3, '365'), (3, '366'),
(4, 'Cristóvão Colombo'), (4, 'Pedro Álvares Cabral'), (4, 'Vasco da Gama'),
(5, 'Verde'), (5, 'Roxo'), (5, 'Laranja'),
(6, 'Terra'), (6, 'Saturno'), (6, 'Júpiter');

-- Dissertativas (últimas 6 questões)
INSERT INTO dissertativa (id_questao, respostaModelo)
SELECT id_questao, respostaModelo FROM (
    VALUES
    (7, 'Causas políticas e sociais que levaram à revolução'),
    (8, 'Processo pelo qual plantas produzem energia'),
    (9, 'Movimento das placas litosféricas'),
    (10, 'Evaporação, condensação, precipitação e infiltração'),
    (11, 'Produto Interno Bruto mede a atividade econômica'),
    (12, 'Darwin e a seleção natural')
) AS t(id_questao, respostaModelo);

-- Avaliação contem questão (ligações)
-- Precisamos pegar os ids de avaliação para associar com as questões
WITH provas AS (SELECT id_prova FROM avaliacao ORDER BY id_prova LIMIT 4),
      questoes AS (SELECT id_questao FROM questao ORDER BY id_questao LIMIT 12)
INSERT INTO avaliacao_contem_questao (id_prova, id_questao) VALUES
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 0 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 6 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 1 LIMIT 1)),

((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 2 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 7 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 3 LIMIT 1)),

((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 4 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 8 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 5 LIMIT 1)),

((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 9 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 10 LIMIT 1)),
((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 11 LIMIT 1));

-- RealizaProva (notas e comentários de alguns alunos para algumas questões)

WITH
provas AS (SELECT id_prova FROM avaliacao ORDER BY id_prova LIMIT 4),
questoes AS (SELECT id_questao FROM questao ORDER BY id_questao LIMIT 12),
alunos AS (SELECT matricula FROM aluno ORDER BY matricula LIMIT 10)
INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 0 LIMIT 1), (SELECT matricula FROM alunos OFFSET 0 LIMIT 1), 4.0, 'Acertou objetiva'),
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 1 LIMIT 1), (SELECT matricula FROM alunos OFFSET 0 LIMIT 1), 3.0, 'Errou capital'),
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 6 LIMIT 1), (SELECT matricula FROM alunos OFFSET 1 LIMIT 1), 2.0, 'Resumo raso'),
((SELECT id_prova FROM provas OFFSET 0 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 0 LIMIT 1), (SELECT matricula FROM alunos OFFSET 2 LIMIT 1), 5.0, 'Correta'),

((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 2 LIMIT 1), (SELECT matricula FROM alunos OFFSET 3 LIMIT 1), 5.0, 'Correta'),
((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 7 LIMIT 1), (SELECT matricula FROM alunos OFFSET 4 LIMIT 1), 4.0, 'Boa explicação'),
((SELECT id_prova FROM provas OFFSET 1 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 3 LIMIT 1), (SELECT matricula FROM alunos OFFSET 5 LIMIT 1), 3.0, 'Faltou detalhar'),

((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 4 LIMIT 1), (SELECT matricula FROM alunos OFFSET 6 LIMIT 1), 4.0, 'Acertou'),
((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 8 LIMIT 1), (SELECT matricula FROM alunos OFFSET 7 LIMIT 1), 3.5, 'Incompleto'),
((SELECT id_prova FROM provas OFFSET 2 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 5 LIMIT 1), (SELECT matricula FROM alunos OFFSET 8 LIMIT 1), 5.0, 'Correta'),

((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 9 LIMIT 1), (SELECT matricula FROM alunos OFFSET 9 LIMIT 1), 5.0, 'Muito bem'),
((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 10 LIMIT 1), (SELECT matricula FROM alunos OFFSET 0 LIMIT 1), 4.0, 'Citou bem o conceito'),
((SELECT id_prova FROM provas OFFSET 3 LIMIT 1), (SELECT id_questao FROM questoes OFFSET 11 LIMIT 1), (SELECT matricula FROM alunos OFFSET 9 LIMIT 1), 2.5, 'Muito resumido');
