import React, { useState, useEffect } from 'react';
import { getProfessores, deleteProfessor } from '../../services/ProfessorService';
import ProfessorForm from './ProfessorForm';

const ProfessoresPage = () => {
    const [professores, setProfessores] = useState([]);
    const [professorEditando, setProfessorEditando] = useState(null);

    useEffect(() => {
        carregarProfessores();
    }, []);

    const carregarProfessores = async () => {
        try {
            const response = await getProfessores();
            setProfessores(response.data);
        } catch (error) {
            console.error("Erro ao carregar professores:", error);
        }
    };

    const handleDelete = async (registro) => {
        if (window.confirm('Tem certeza que deseja excluir este professor?')) {
            await deleteProfessor(registro);
            carregarProfessores();
        }
    };

    return (
        <div className="questoes-page-container">
            <h1>Gerenciamento de Professores</h1>

            <div className="form-section">
                <ProfessorForm
                    professor={professorEditando}
                    onSave={() => {
                        setProfessorEditando(null);
                        carregarProfessores();
                    }}
                    onCancel={() => setProfessorEditando(null)}
                />
            </div>

            <div className="list-section">
                <h2>Lista de Professores</h2>
                <div className="questoes-grid">
                    {professores.map((professor) => (
                        <div key={professor.registro} className="questao-card">
                            <h3>{`${professor.pnome} ${professor.snome || ''}`}</h3>
                            <p>
                                <strong>Registro:</strong> {professor.registro} <br />
                                <strong>CPF:</strong> {professor.cpf}
                            </p>
                            <div className="card-actions">
                                <button onClick={() => setProfessorEditando(professor)} className="edit-btn">
                                    Editar
                                </button>
                                <button onClick={() => handleDelete(professor.registro)} className="delete-btn">
                                    Excluir
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default ProfessoresPage;