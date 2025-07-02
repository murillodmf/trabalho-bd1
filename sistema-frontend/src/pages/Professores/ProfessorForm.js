import React, { useState, useEffect } from 'react';
import { createProfessor, updateProfessor } from '../../services/ProfessorService';

const ProfessorForm = ({ professor, onSave, onCancel }) => {
    // CORREÇÃO: O estado interno agora usa os mesmos nomes do backend (pnome, snome)
    const [formData, setFormData] = useState({
        registro: '',
        pnome: '',
        snome: '',
        cpf: '',
        idade: ''
    });

    // CORREÇÃO: O useEffect agora preenche 'pnome' e 'snome' corretamente
    useEffect(() => {
        if (professor) {
            setFormData({
                registro: professor.registro || '',
                pnome: professor.pnome || '', // Usando a propriedade correta
                snome: professor.snome || '', // Usando a propriedade correta
                cpf: professor.cpf || '',
                idade: professor.idade || ''
            });
        } else {
            // Limpa o formulário quando o modo de edição é cancelado
            setFormData({ registro: '', pnome: '', snome: '', cpf: '', idade: '' });
        }
    }, [professor]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (professor) {
            await updateProfessor(professor.registro, formData);
        } else {
            await createProfessor(formData);
        }
        onSave();
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    return (
        <form onSubmit={handleSubmit} className="questao-form">
            <h3>{professor ? 'Editar Professor' : 'Adicionar Novo Professor'}</h3>

            <div className="form-group">
                <label htmlFor="registro">Registro:</label>
                <input
                    id="registro"
                    name="registro"
                    type="number"
                    value={formData.registro}
                    onChange={handleChange}
                    required
                    disabled={!!professor}
                />
            </div>

            <div className="form-group">
                <label htmlFor="pnome">Primeiro Nome:</label>
                {/* CORREÇÃO: O 'name' e 'value' agora correspondem ao estado (pnome) */}
                <input
                    id="pnome"
                    name="pnome"
                    value={formData.pnome}
                    onChange={handleChange}
                    placeholder="Primeiro Nome"
                    required
                />
            </div>

            <div className="form-group">
                <label htmlFor="snome">Sobrenome:</label>
                {/* CORREÇÃO: O 'name' e 'value' agora correspondem ao estado (snome) */}
                <input
                    id="snome"
                    name="snome"
                    value={formData.snome}
                    onChange={handleChange}
                    placeholder="Sobrenome"
                />
            </div>

            <div className="form-group">
                <label htmlFor="cpf">CPF:</label>
                <input
                    id="cpf"
                    name="cpf"
                    value={formData.cpf}
                    onChange={handleChange}
                    placeholder="CPF"
                    required
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
                    placeholder="Idade"
                />
            </div>

            <div className="form-actions">
                <button type="submit" className="btn-save">{professor ? 'Atualizar' : 'Salvar'}</button>
                {professor && <button type="button" onClick={onCancel} className="btn-cancel">Cancelar</button>}
            </div>
        </form>
    );
};

export default ProfessorForm;