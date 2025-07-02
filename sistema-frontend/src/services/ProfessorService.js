import axios from 'axios';

const API_URL = 'http://localhost:8080/professores';

export const getProfessores = async () => {
  return await axios.get(API_URL);
};

export const getProfessor = async (registro) => {
  return await axios.get(`${API_URL}/${registro}`);
};

export const createProfessor = async (professor) => {
  return await axios.post(API_URL, professor);
};

export const updateProfessor = async (registro, professor) => {
  return await axios.put(`${API_URL}/${registro}`, professor);
};

export const deleteProfessor = async (registro) => {
  return await axios.delete(`${API_URL}/${registro}`);
};