import React, { useState, useEffect } from 'react';
// A importação do useEffect foi adicionada para a lógica de edição que você já tinha
import { createAluno, updateAluno, getAlunoById } from '../../services/AlunoService'; // Supondo que você tenha getAlunoById
import { useNavigate } from 'react-router-dom';


const AlunoForm = ({ aluno, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        matricula: '',
        cpf: '',
        pnome: '',
        snome: '',
        idade: ''
    });

    // Lógica para preencher o formulário ao editar (mantida)
    useEffect(() => {
        if (aluno) {
            setFormData(aluno);
        } else {
            // Limpa o formulário quando não está editando
            setFormData({
                matricula: '',
                cpf: '',
                pnome: '',
                snome: '',
                idade: ''
            });
        }
    }, [aluno]);


    const handleSubmit = async (e) => {
        e.preventDefault();
        // Lógica de salvar/atualizar (inalterada)
        if (aluno) {
            await updateAluno(aluno.matricula, formData);
        } else {
            await createAluno(formData);
        }
        onSave();
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({...prev, [name]: value}));
    };

    return (
        // Adicionada uma classe ao formulário para estilização geral
        <form onSubmit={handleSubmit} className="form-container">
            <h3>{aluno ? 'Editar Aluno' : 'Adicionar Novo Aluno'}</h3>

            {/* Cada campo agora está dentro de um 'form-group' */}
            <div className="form-group">
                <label htmlFor="matricula">Matrícula:</label>
                <input
                    id="matricula"
                    name="matricula"
                    type="number" // Ajustado o tipo para número
                    value={formData.matricula}
                    onChange={handleChange}
                    placeholder="Matrícula"
                    required
                    disabled={!!aluno} // A lógica de desabilitar na edição foi mantida
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
                <label htmlFor="pnome">Primeiro Nome:</label>
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
                <input
                    id="snome"
                    name="snome"
                    value={formData.snome}
                    onChange={handleChange}
                    placeholder="Sobrenome"
                    // O sobrenome pode não ser obrigatório, então o 'required' foi removido para flexibilidade
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
                    // A idade pode não ser obrigatória
                />
            </div>

            {/* Div para agrupar os botões */}
            <div className="form-actions">
                <button type="submit" className="btn btn-primary">Salvar</button>
                {/* Lógica do botão de cancelar (inalterada) */}
                {aluno && <button type="button" onClick={onCancel} className="btn btn-secondary">Cancelar</button>}
            </div>
        </form>
    );
};

export default AlunoForm;