import React, { useState, useEffect } from 'react';
import { getTurmas, deleteTurma } from '../../services/TurmaService';
import TurmaForm from './TurmaForm';

const TurmasPage = () => {
    const [turmas, setTurmas] = useState([]);
    const [turmaEditando, setTurmaEditando] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        carregarTurmas();
    }, []);

    const carregarTurmas = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await getTurmas();
            setTurmas(response.data);
        } catch (err) {
            setError("Erro ao carregar turmas.");
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (cod) => {
        if (window.confirm('Tem certeza que deseja excluir esta turma?')) {
            await deleteTurma(cod);
            carregarTurmas();
        }
    };

    return (
        <div className="questoes-page-container">
            <h1>Gerenciamento de Turmas</h1>
            {error && <div className="error-message">{error}</div>}

            <div className="form-section">
                <TurmaForm
                    turma={turmaEditando}
                    onSave={() => {
                        setTurmaEditando(null);
                        carregarTurmas();
                    }}
                    onCancel={() => setTurmaEditando(null)}
                />
            </div>

            <div className="list-section">
                <h2>Lista de Turmas</h2>
                {loading ? (
                    <p>Carregando...</p>
                ) : (
                    <div className="questoes-grid">
                        {turmas.map((turma) => (
                            <div key={turma.cod} className="questao-card">
                                <h3>{turma.materia}</h3>
                                <p>
                                    <strong>Código:</strong> {turma.cod} <br />
                                    <strong>Alunos:</strong> {turma.quantidadeAlunos} <br />
                                    <strong>Registro Professor:</strong> {turma.registroProfessor || 'Não atribuído'}
                                </p>
                                <div className="card-actions">
                                    <button onClick={() => setTurmaEditando(turma)} className="edit-btn">
                                        Editar
                                    </button>
                                    <button onClick={() => handleDelete(turma.cod)} className="delete-btn">
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

export default TurmasPage;