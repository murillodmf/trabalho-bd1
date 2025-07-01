import React, {useEffect, useState} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createAluno, getAlunoById, updateAluno} from '../api/alunoService';

function AlunoFormPage() {
    const navigate = useNavigate(); // Inicializa o hook de navegação
    const { matricula } = useParams(); // Pega o parâmetro 'matricula' da URL. Se não houver, será undefined.
    const isEditing = Boolean(matricula);

    // Cria um estado para guardar os dados do formulário
    const [aluno, setAluno] = useState({
        matricula: '',
        cpf: '',
        pnome: '',
        snome: '',
        idade: ''
    });

    // useEffect para buscar os dados do aluno se estivermos em modo de edição
    useEffect(() => {
        if (isEditing) {
            getAlunoById(matricula)
                .then(response => {
                    setAluno(response.data); // Preenche o formulário com os dados do aluno
                })
                .catch(error => {
                    console.error('Erro ao buscar aluno:', error);
                    alert('Não foi possível carregar os dados do aluno para edição.');
                });
        }
    }, [matricula, isEditing]); // Executa quando a matrícula ou o modo de edição mudam

    // Função para atualizar o estado quando o usuário digita em um campo
    const handleChange = (event) => {
        const { name, value } = event.target;
        setAluno(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            if (isEditing) {
                // Se estiver editando, chama a função de ATUALIZAR
                await updateAluno(matricula, aluno);
                alert('Aluno atualizado com sucesso!');
            } else {
                // Se não, chama a função de CRIAR
                await createAluno(aluno);
                alert('Aluno salvo com sucesso!');
            }
            navigate('/alunos'); // Redireciona para a lista
        } catch (error) {
            console.error('Erro ao salvar o aluno:', error);
            alert('Falha ao salvar o aluno.');
        }
    };

    return (
        <div className="form-container">
            <h1>{isEditing ? 'Editar Aluno' : 'Cadastrar Novo Aluno'}</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Matrícula:</label>
                    <input type="number" name="matricula" value={aluno.matricula} onChange={handleChange} required disabled={isEditing} />
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