import axios from 'axios';

const API_URL = 'http://localhost:8080/questoes';

export const getQuestoes = async () => {
  return await axios.get(API_URL);
};

export const getQuestao = async (id) => {
  return await axios.get(`${API_URL}/${id}`);
};

export const createQuestao = async (questao) => {
  return await axios.post(API_URL, questao);
};

export const updateQuestao = async (id, questao) => {
  return await axios.put(`${API_URL}/${id}`, questao);
};

export const deleteQuestao = async (id) => {
  return await axios.delete(`${API_URL}/${id}`);
};