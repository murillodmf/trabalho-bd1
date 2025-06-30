import api from './api';

// Função para buscar TODOS os alunos
export const getAllAlunos = () => {
    return api.get('/alunos');
};

// Função para buscar UM aluno pela sua matrícula
export const getAlunoById = (matricula) => {
    return api.get(`/alunos/${matricula}`);
};

// Função para CRIAR um novo aluno
export const createAluno = (alunoData) => {
    return api.post('/alunos', alunoData);
};

// Função para ATUALIZAR um aluno existente
export const updateAluno = (matricula, alunoData) => {
    return api.put(`/alunos/${matricula}`, alunoData);
};

// Função para DELETAR um aluno
export const deleteAluno = (matricula) => {
    return api.delete(`/alunos/${matricula}`);
};