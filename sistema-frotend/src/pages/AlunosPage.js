import React, { useState, useEffect } from 'react';
import { getAllAlunos, deleteAluno } from '../api/alunoService';
import { Link } from 'react-router-dom';

function AlunosPage() {
    const [alunos, setAlunos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchAlunos = async () => {
            try {
                const response = await getAllAlunos();
                setAlunos(response.data);
            } catch (err) {
                setError('Falha ao buscar alunos. Verifique a conexão com o backend.');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchAlunos();
    }, []);

    if (loading) {
        return <p>Carregando alunos...</p>;
    }

    if (error) {
        return <p style={{ color: 'red' }}>{error}</p>;
    }

    const handleDelete = async (matricula) => {
        // Pede a confirmação do usuário
        const isConfirmed = window.confirm('Tem certeza que deseja excluir este aluno? Esta ação não pode ser desfeita.');

        // Se o usuário não confirmar, a função para por aqui
        if (!isConfirmed) {
            return;
        }

        // Se o usuário confirmar, tenta deletar
        try {
            await deleteAluno(matricula); // Chama a função da API

            // ATUALIZA A TELA: Filtra a lista de alunos, removendo o que foi deletado
            setAlunos(alunos.filter(aluno => aluno.matricula !== matricula));

            alert('Aluno excluído com sucesso!');
        } catch (err) {
            setError('Falha ao excluir o aluno. Tente novamente.');
            console.error(err);
        }
    };

    return (
        <div className="page-container">
            <h1>Gerenciamento de Alunos</h1>
            <Link to="/alunos/novo">
                <button>Adicionar Novo Aluno</button>
            </Link>

            <table className="data-table">
                <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nome Completo</th>
                    <th>CPF</th>
                    <th>Idade</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {alunos.length > 0 ? (
                    alunos.map(aluno => (
                        <tr key={aluno.matricula}>
                            <td>{aluno.matricula}</td>
                            <td>{`${aluno.pnome} ${aluno.snome || ''}`}</td>
                            <td>{aluno.cpf}</td>
                            <td>{aluno.idade}</td>
                            <td>
                                <Link to={`/alunos/editar/${aluno.matricula}`}>
                                    <button>Editar</button>
                                </Link>
                                <button onClick={() => handleDelete(aluno.matricula)} className="delete-button">Excluir</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="5">Nenhum aluno encontrado.</td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}

export default AlunosPage;