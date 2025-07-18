import React, { useState, useEffect } from 'react';
import { createTurma, updateTurma } from '../../services/TurmaService';
import { getProfessores } from '../../services/ProfessorService';

const TurmaForm = ({ turma, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        cod: '',
        materia: '',
        quantidadeAlunos: 0,
        registroProfessor: ''
    });

    const [professores, setProfessores] = useState([]);

    useEffect(() => {
        const carregarProfessores = async () => {
            try {
                const response = await getProfessores();
                setProfessores(response.data);
            } catch (error) {
                console.error("Erro ao carregar lista de professores:", error);
            }
        };

        carregarProfessores();
    }, []);

    useEffect(() => {
        if (turma) {
            setFormData({
                ...turma,
                registroProfessor: turma.registroProfessor ? String(turma.registroProfessor) : ''
            });
        } else {
            setFormData({ cod: '', materia: '', quantidadeAlunos: 0, registroProfessor: '' });
        }
    }, [turma]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!formData.registroProfessor) {
            alert('Por favor, selecione um professor.');
            return;
        }
        if (turma) {
            await updateTurma(turma.cod, formData);
        } else {
            await createTurma(formData);
        }
        onSave();
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>{turma ? 'Editar Turma' : 'Adicionar Nova Turma'}</h3>

            <div className="form-group">
                <label htmlFor="cod">Código da Turma:</label>
                <input id="cod" name="cod" type="number" value={formData.cod} onChange={handleChange} required disabled={!!turma} />
            </div>

            <div className="form-group">
                <label htmlFor="materia">Matéria:</label>
                <input id="materia" name="materia" type="text" value={formData.materia} onChange={handleChange} placeholder="Nome da Matéria" required />
            </div>
        

            <div className="form-group">
                <label htmlFor="registroProfessor">Professor:</label>
                <select
                    id="registroProfessor"
                    name="registroProfessor"
                    value={formData.registroProfessor}
                    onChange={handleChange}
                    required
                >
                    <option value="">Selecione um professor...</option>
                    {professores.map(prof => (
                        <option key={prof.registro} value={prof.registro}>
                            {`${prof.pnome} ${prof.snome || ''}`} (Registro: {prof.registro})
                        </option>
                    ))}
                </select>
            </div>

            <div className="form-actions">
                <button type="submit" className="btn btn-primary">{turma ? 'Atualizar' : 'Salvar'}</button>
                {turma && <button type="button" onClick={onCancel} className="btn btn-secondary">Cancelar</button>}
            </div>
        </form>
    );
};

export default TurmaForm;