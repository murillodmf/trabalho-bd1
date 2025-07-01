import axios from 'axios';

const API_URL = 'http://localhost:8080/dissertativas';

export const createDissertativa = async (dissertativa) => {
  return await axios.post(API_URL, dissertativa);
};

export const updateDissertativa = async (id, dissertativa) => {
  return await axios.put(`${API_URL}/${id}`, dissertativa);
};

export const deleteDissertativa = async (id) => {
  return await axios.delete(`${API_URL}/${id}`);
};