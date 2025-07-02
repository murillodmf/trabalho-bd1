import axios from 'axios';

const API_URL = 'http://localhost:8080/turmas';

export const getTurmas = async () => {
  return await axios.get(API_URL);
};

export const getTurma = async (codigo) => {
  return await axios.get(`${API_URL}/${codigo}`);
};

export const createTurma = async (turma) => {
  return await axios.post(API_URL, turma);
};

export const updateTurma = async (codigo, turma) => {
  return await axios.put(`${API_URL}/${codigo}`, turma);
};

export const deleteTurma = async (codigo) => {
  return await axios.delete(`${API_URL}/${codigo}`);
};