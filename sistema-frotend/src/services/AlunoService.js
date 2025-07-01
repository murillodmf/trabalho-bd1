import axios from 'axios';

const API_URL = 'http://localhost:8080/alunos';

export const getAlunos = async () => {
  return await axios.get(API_URL);
};

export const getAluno = async (matricula) => {
  return await axios.get(`${API_URL}/${matricula}`);
};

export const createAluno = async (aluno) => {
  return await axios.post(API_URL, aluno);
};

export const updateAluno = async (matricula, aluno) => {
  return await axios.put(`${API_URL}/${matricula}`, aluno);
};

export const deleteAluno = async (matricula) => {
  return await axios.delete(`${API_URL}/${matricula}`);
};