import axios from 'axios';

const API_URL = 'http://localhost:8080/realizaProva';

export const getRealizacoes = async () => {
  return await axios.get(API_URL);
};

export const createRealizacao = async (realizacao) => {
  return await axios.post(API_URL, realizacao);
};

export const updateRealizacao = async (idProva, idQuestao, matricula, realizacao) => {
  return await axios.put(`${API_URL}/${idProva}/${idQuestao}/${matricula}`, realizacao);
};

export const deleteRealizacao = async (idProva, idQuestao, matricula) => {
  return await axios.delete(`${API_URL}/${idProva}/${idQuestao}/${matricula}`);
};