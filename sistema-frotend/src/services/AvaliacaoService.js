import api from '../api/api';

const API_URL_AVALIACOES = '/avaliacoes';

export const getAvaliacoes = async () => {
  return await api.get(API_URL_AVALIACOES);
};

export const getAvaliacao = async (id) => {
  return await api.get(`${API_URL_AVALIACOES}/${id}`);
};

export const createAvaliacao = async (avaliacaoDTO) => {
  return await api.post(API_URL_AVALIACOES, avaliacaoDTO);
};

export const updateAvaliacao = async (id, avaliacaoDTO) => {
  return await api.put(`${API_URL_AVALIACOES}/${id}`, avaliacaoDTO);
};

export const deleteAvaliacao = async (id) => {
  return await api.delete(`${API_URL_AVALIACOES}/${id}`);
};