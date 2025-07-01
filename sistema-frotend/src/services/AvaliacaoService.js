import axios from 'axios';

const API_URL = 'http://localhost:8080/avaliacoes';

export const getAvaliacoes = async () => {
  return await axios.get(API_URL);
};

export const getAvaliacao = async (id) => {
  return await axios.get(`${API_URL}/${id}`);
};

export const createAvaliacao = async (avaliacao) => {
  return await axios.post(API_URL, avaliacao);
};

export const updateAvaliacao = async (id, avaliacao) => {
  return await axios.put(`${API_URL}/${id}`, avaliacao);
};

export const deleteAvaliacao = async (id) => {
  return await axios.delete(`${API_URL}/${id}`);
};