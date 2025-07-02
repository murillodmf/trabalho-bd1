import axios from 'axios';

const API_URL = 'http://localhost:8080/objetivas';

export const createObjetiva = async (objetiva) => {
  return await axios.post(API_URL, objetiva);
};

export const updateObjetiva = async (id, objetiva) => {
  return await axios.put(`${API_URL}/${id}`, objetiva);
};

export const deleteObjetiva = async (id) => {
  return await axios.delete(`${API_URL}/${id}`);
};