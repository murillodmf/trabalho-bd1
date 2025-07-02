import api from '../api/api';

const API_URL_QUESTOES = '/questoes';
const API_URL_QUESTOES_COMPLETA = '/questoes/completa';

export const getQuestoes = async () => {
  return await api.get(API_URL_QUESTOES);
};

export const getQuestaoCompleta = async (id) => {
  return await api.get(`${API_URL_QUESTOES_COMPLETA}/${id}`);
};

export const createQuestaoCompleta = async (questao) => {
  return await api.post(API_URL_QUESTOES_COMPLETA, questao);
};

export const updateQuestaoCompleta = async (id, questao) => {
  return await api.put(`${API_URL_QUESTOES_COMPLETA}/${id}`, questao);
};

export const deleteQuestao = async (id) => {
  return await api.delete(`${API_URL_QUESTOES}/${id}`);
};