import api from './api'; // Importa nossa instância configurada do Axios

// Função para buscar TODOS os professores
export const getAllProfessores = () => {
    return api.get('/professores');
};

// Função para buscar UM professor pelo seu ID (registro)
export const getProfessorById = (id) => {
    return api.get(`/professores/${id}`);
};

// Função para CRIAR um novo professor
// O parâmetro 'professorData' será um objeto JSON com nome e cpf
export const createProfessor = (professorData) => {
    return api.post('/professores', professorData);
};

// Função para ATUALIZAR um professor existente
export const updateProfessor = (id, professorData) => {
    return api.put(`/professores/${id}`, professorData);
};

// Função para DELETAR um professor
export const deleteProfessor = (id) => {
    return api.delete(`/professores/${id}`);
};