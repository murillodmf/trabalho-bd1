-- Consulta 1: Desempenho de Alunos por Avaliação
SELECT 
    al.matricula, 
    al.pnome, 
    al.snome, 
    rp.id_prova, 
    AVG(rp.nota) AS media_nota
FROM realizaProva rp
JOIN aluno al ON rp.matricula = al.matricula
GROUP BY al.matricula, al.pnome, al.snome, rp.id_prova
ORDER BY media_nota DESC;

-- Consulta 2: Professores com mais turmas e códigos
SELECT 
    p.nome, 
    COUNT(t.cod) AS total_turmas, 
    GROUP_CONCAT(t.cod ORDER BY t.cod) AS codigos_turmas
FROM professor p
JOIN turma t ON p.registro = t.registro
GROUP BY p.nome
ORDER BY total_turmas DESC;

-- Consulta 3: Ordenar alunos de uma determinada prova pelas notas
SELECT 
    al.matricula, 
    al.pnome, 
    al.snome, 
    rp.id_prova, 
    AVG(rp.nota) AS media_nota
FROM realizaProva rp
JOIN aluno al ON rp.matricula = al.matricula
WHERE rp.id_prova = 500  -- Altere o ID conforme necessário
GROUP BY al.matricula, al.pnome, al.snome, rp.id_prova
ORDER BY media_nota DESC;

-- Consulta 4: Quantidade de Alunos por Turma
SELECT 
    t.cod AS turma, 
    t.materia, 
    t.quantidadeAlunos
FROM turma t
ORDER BY t.quantidadeAlunos DESC;

-- Consulta 5: Quem já realizou uma determinada prova
SELECT DISTINCT 
    al.matricula, 
    al.pnome, 
    al.snome
FROM realizaProva rp
JOIN aluno al ON rp.matricula = al.matricula
WHERE rp.id_prova = 500;  -- Altere o ID conforme necessário

-- Consulta 6: Frequência de respostas mais comuns por alternativa
SELECT 
    orr.id_questao, 
    q.enunciado, 
    orr.alternativa, 
    COUNT(*) AS total_respostas
FROM objetiva_resposta orr
JOIN questao q ON orr.id_questao = q.id_questao
GROUP BY orr.id_questao, q.enunciado, orr.alternativa
ORDER BY total_respostas DESC;
