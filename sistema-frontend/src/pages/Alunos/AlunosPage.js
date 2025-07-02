
import React, { useState, useEffect } from 'react';
import { getAlunos, deleteAluno } from '../../services/AlunoService';
import AlunoForm from './AlunoForm';

const AlunosPage = () => {
    const [alunos, setAlunos] = useState([]);
    const [alunoEditando, setAlunoEditando] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Lógica de carregar e deletar permanece a mesma
    useEffect(() => {
        carregarAlunos();
    }, []);

    const carregarAlunos = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await getAlunos();
            setAlunos(response.data);
        } catch (err) {
            setError("Erro ao carregar alunos.");
            console.error("Erro ao carregar alunos:", err);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (matricula) => {
        if (window.confirm('Tem certeza que deseja excluir este aluno?')) {
            await deleteAluno(matricula);
            carregarAlunos();
        }
    };

    return (
        // Aplicando o container principal do seu novo CSS
        <div className="questoes-page-container">
            <h1>Gerenciamento de Alunos</h1>
            {error && <div className="error-message">{error}</div>}

            {/* Seção do Formulário */}
            <div className="form-section">
                <AlunoForm
                    aluno={alunoEditando}
                    onSave={() => {
                        setAlunoEditando(null);
                        carregarAlunos();
                    }}
                    onCancel={() => setAlunoEditando(null)}
                />
            </div>

            {/* Seção da Lista, agora com Cards */}
            <div className="list-section">
                <h2>Lista de Alunos</h2>
                {loading ? (
                    <p>Carregando...</p>
                ) : (
                    <div className="questoes-grid">
                        {alunos.map((aluno) => (
                            <div key={aluno.matricula} className="questao-card">
                                {/* Conteúdo do Card */}
                                <h3>{`${aluno.pnome} ${aluno.snome || ''}`}</h3>
                                <p>
                                    <strong>Matrícula:</strong> {aluno.matricula} <br />
                                    <strong>CPF:</strong> {aluno.cpf}
                                </p>
                                {/* Ações do Card */}
                                <div className="card-actions">
                                    <button onClick={() => setAlunoEditando(aluno)} className="edit-btn">
                                        Editar
                                    </button>
                                    <button onClick={() => handleDelete(aluno.matricula)} className="delete-btn">
                                        Excluir
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default AlunosPage;