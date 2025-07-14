import React, { useState, useEffect } from 'react';
import { createAluno, updateAluno } from '../../services/AlunoService';

const AlunoForm = ({ aluno, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        matricula: '', cpf: '', pnome: '', snome: '', idade: ''
    });

    useEffect(() => {
        if (aluno) {
            setFormData(aluno);
        } else {
            setFormData({ matricula: '', cpf: '', pnome: '', snome: '', idade: '' });
        }
    }, [aluno]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (aluno) {
            await updateAluno(aluno.matricula, formData);
        } else {
            await createAluno(formData);
        }
        onSave();
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    return (
        <form onSubmit={handleSubmit} className="questao-form">
            <h3>{aluno ? 'Editar Aluno' : 'Adicionar Novo Aluno'}</h3>

            <div className="form-group">
                <label htmlFor="matricula">Matrícula:</label>
                <input
                    id="matricula"
                    name="matricula"
                    type="number"
                    value={formData.matricula}
                    onChange={handleChange}
                    required
                    disabled={!!aluno}
                />
            </div>

            <div className="form-group">
                <label htmlFor="cpf">CPF:</label>
                <input
                    id="cpf"
                    name="cpf"
                    value={formData.cpf}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label htmlFor="pnome">Primeiro Nome:</label>
                <input
                    id="pnome"
                    name="pnome"
                    value={formData.pnome}
                    onChange={handleChange}
                    required
                />
            </div>

            <div className="form-group">
                <label htmlFor="snome">Sobrenome:</label>
                <input
                    id="snome"
                    name="snome"
                    value={formData.snome}
                    onChange={handleChange}
                />
            </div>

            <div className="form-group">
                <label htmlFor="idade">Idade:</label>
                <input
                    id="idade"
                    name="idade"
                    type="number"
                    value={formData.idade}
                    onChange={handleChange}
                />
            </div>

            <div className="form-actions">
                <button type="submit" className="btn-save">Salvar</button>
                {aluno && <button type="button" onClick={onCancel} className="btn-cancel">Cancelar</button>}
            </div>
        </form>
    );
};

export default AlunoForm;