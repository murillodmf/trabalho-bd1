import React, { useState, useEffect } from 'react';
import { getAlunoTurmas, deleteAlunoTurma } from '../../services/AlunoTurmaService';
import { getTurmas } from '../../services/TurmaService';
import { getAlunos } from '../../services/AlunoService';
import AlunoTurmaForm from './AlunoTurmaForm';
import './AlunoTurmaPage.css';

const AlunoTurmaPage = () => {
    const [alunoTurmas, setAlunoTurmas] = useState([]);
    const [turmas, setTurmas] = useState([]);
    const [alunos, setAlunos] = useState([]);
    const [selectedTurma, setSelectedTurma] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        carregarDados();
    }, []);

    const carregarDados = async () => {
        try {
            setError(null);
            const [alunoTurmasRes, turmasRes, alunosRes] = await Promise.all([
                getAlunoTurmas(),
                getTurmas(),
                getAlunos()
            ]);
            setAlunoTurmas(alunoTurmasRes.data || []);
            setTurmas(turmasRes.data || []);
            setAlunos(alunosRes.data || []);
        } catch (error) {
            setError("Erro ao carregar dados da página de matrículas.");
            console.error("Erro ao carregar dados:", error);
        }
    };

    const handleDelete = async (matricula, codTurma) => {
        if (window.confirm('Tem certeza que deseja remover este aluno da turma?')) {
            await deleteAlunoTurma(matricula, codTurma);
            setShowModal(false);
            carregarDados();
        }
    };

    const handleTurmaClick = (turma) => {
        setSelectedTurma(turma);
        setShowModal(true);
    };

    const getAlunosNaTurma = (codTurma) => {
        return alunoTurmas
            .filter(at => at.codTurma === codTurma)
            .map(at => ({
                matricula: at.matricula,
                nomeCompleto: at.nomeAluno
            }));
    };

    return (
        <div className="page-container">
            <h1>Matrículas de Alunos</h1>
            {error && <p className="error-message">{error}</p>}

            <div className="section-card">
                <AlunoTurmaForm
                    onSave={carregarDados}
                    turmas={turmas}
                    alunos={alunos}
                />
            </div>

            <div className="section-card">
                <h2>Lista de Turmas</h2>
                {turmas.length === 0 ? (
                    <p>Nenhuma turma cadastrada</p>
                ) : (
                    <div className="grid-container">
                        {turmas.map((turma) => (
                            <div
                                key={turma.cod}
                                className="item-card clickable-card"
                                onClick={() => handleTurmaClick(turma)}
                            >
                                <div>
                                    <h3>{turma.materia}</h3>
                                    <p>
                                        <strong>Código:</strong> {turma.cod}<br />
                                        <strong>Alunos matriculados:</strong> {turma.quantidadeAlunos}
                                    </p>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>

            {showModal && selectedTurma && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h2>Alunos na Turma: {selectedTurma.materia}</h2>
                            <button onClick={() => setShowModal(false)} className="modal-close-btn">&times;</button>
                        </div>
                        <div className="modal-body">
                            {getAlunosNaTurma(selectedTurma.cod).length === 0 ? (
                                <p style={{ textAlign: 'center', color: '#666' }}>
                                    Nenhum aluno matriculado nesta turma.
                                </p>
                            ) : (
                                <table className="data-table">
                                    <thead>
                                        <tr>
                                            <th>Matrícula</th>
                                            <th>Nome</th>
                                            <th>Ações</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {getAlunosNaTurma(selectedTurma.cod).map(aluno => (
                                            <tr key={aluno.matricula}>
                                                <td>{aluno.matricula}</td>
                                                <td>{aluno.nomeCompleto}</td>
                                                <td>
                                                    <button
                                                        onClick={() => handleDelete(aluno.matricula, selectedTurma.cod)}
                                                        className="btn btn-danger"
                                                    >
                                                        Remover
                                                    </button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AlunoTurmaPage;
