import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Hook para redirecionar o usuário
import { createAluno } from '../api/alunoService'; // Nossa função de API

function AlunoFormPage() {
    const navigate = useNavigate(); // Inicializa o hook de navegação

    // Cria um estado para guardar os dados do formulário
    const [aluno, setAluno] = useState({
        matricula: '',
        cpf: '',
        pnome: '',
        snome: '',
        idade: ''
    });

    // Função para atualizar o estado quando o usuário digita em um campo
    const handleChange = (event) => {
        const { name, value } = event.target;
        setAluno(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    // Função para lidar com o envio do formulário
    const handleSubmit = async (event) => {
        event.preventDefault(); // Previne o recarregamento padrão da página
        try {
            // Chama a função para criar o aluno no backend
            await createAluno(aluno);
            alert('Aluno salvo com sucesso!');
            // Redireciona o usuário de volta para a lista de alunos após o sucesso
            navigate('/alunos');
        } catch (error) {
            console.error('Erro ao salvar o aluno:', error);
            alert('Falha ao salvar o aluno. Verifique o console para mais detalhes.');
        }
    };

    return (
        <div className="form-container">
            <h1>Cadastrar Novo Aluno</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Matrícula:</label>
                    <input type="number" name="matricula" value={aluno.matricula} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>CPF:</label>
                    <input type="text" name="cpf" value={aluno.cpf} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Primeiro Nome:</label>
                    <input type="text" name="pnome" value={aluno.pnome} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Sobrenome:</label>
                    <input type="text" name="snome" value={aluno.snome} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Idade:</label>
                    <input type="number" name="idade" value={aluno.idade} onChange={handleChange} />
                </div>
                <button type="submit">Salvar Aluno</button>
            </form>
        </div>
    );
}

export default AlunoFormPage;