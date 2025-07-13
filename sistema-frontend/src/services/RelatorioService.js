import api from '../api/api';

const API_URL = '/relatorios';

export const getMediaGeralPorTurma = async (materia) => {
    if (!materia) return { data: [] };
    return await api.get(`${API_URL}/media-geral-por-turma/${materia}`);
};

export const getAcertosPorQuestao = async (idQuestao) => {
    if (!idQuestao) return { data: null };
    return await api.get(`${API_URL}/acertos-por-questao/${idQuestao}`);
};

export const getRankingAlunosPorTurma = async (codTurma) => {
    if (!codTurma) return { data: [] };
    return await api.get(`${API_URL}/ranking-alunos/turma/${codTurma}`);
};

export const getEvolucaoComparativa = async (matriculaAluno, codTurma) => {
    if (!matriculaAluno || !codTurma) return { data: [] };
    return await api.get(`${API_URL}/evolucao-comparativa/aluno/${matriculaAluno}/turma/${codTurma}`);
};