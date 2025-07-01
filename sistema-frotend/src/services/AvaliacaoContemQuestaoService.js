import axios from 'axios';

const API_URL = 'http://localhost:8080/avaliacaoQuestoes';

export const createAvaliacaoQuestao = async (avaliacaoQuestao) => {
  return await axios.post(API_URL, avaliacaoQuestao);
};

export const deleteByProva = async (idProva) => {
  return await axios.delete(`${API_URL}/prova/${idProva}`);
};

export const deleteByQuestao = async (idQuestao) => {
  return await axios.delete(`${API_URL}/questao/${idQuestao}`);
};