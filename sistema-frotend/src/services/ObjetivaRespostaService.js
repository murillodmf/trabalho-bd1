import axios from 'axios';

const API_URL = 'http://localhost:8080/objetivaRespostas';

export const createObjetivaResposta = async (resposta) => {
  return await axios.post(API_URL, resposta);
};

export const deleteResposta = async (idQuestao, alternativa) => {
  return await axios.delete(`${API_URL}/${idQuestao}/${alternativa}`);
};

export const deleteAllRespostas = async (idQuestao) => {
  return await axios.delete(`${API_URL}/questao/${idQuestao}`);
};