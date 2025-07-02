import api from '../api/api';

const API_URL = '/realizaProva';

export const getRealizacoes = async () => {
  return await api.get(API_URL);
};

export const getRealizacoesPorAvaliacao = async (idProva) => {
  return await api.get(`${API_URL}/avaliacao/${idProva}`);
};

export const getRealizacaoDetalhada = async (idProva, idQuestao, matricula) => {
  return await api.get(`${API_URL}/${idProva}/${idQuestao}/${matricula}`);
};

export const createRealizacao = async (realizacao) => {
  return await api.post(API_URL, realizacao);
};

export const updateRealizacao = async (idProva, idQuestao, matricula, realizacao) => {
  return await api.put(`${API_URL}/${idProva}/${idQuestao}/${matricula}`, realizacao);
};

export const deleteRealizacao = async (idProva, idQuestao, matricula) => {
  return await api.delete(`${API_URL}/${idProva}/${idQuestao}/${matricula}`);
};