import axios from 'axios';

const API_URL = 'http://localhost:8080/alunos-turmas';

export const getAlunoTurmas = async () => {
  return await axios.get(API_URL);
};

export const createAlunoTurma = async (alunoTurma) => {
  return await axios.post(API_URL, alunoTurma);
};

export const deleteAlunoTurma = async (matricula, cod) => {
  return await axios.delete(`${API_URL}/${matricula}/${cod}`);
};