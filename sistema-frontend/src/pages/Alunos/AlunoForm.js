import React, { useState } from 'react';
import { createAluno, updateAluno } from '../../services/AlunoService';

const AlunoForm = ({ aluno, onSave, onCancel }) => {
  const [formData, setFormData] = useState(aluno || {
    matricula: '',
    cpf: '',
    pnome: '',
    snome: '',
    idade: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (aluno) {
      await updateAluno(aluno.matricula, formData);
    } else {
      await createAluno(formData);
    }
    onSave();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="matricula"
        value={formData.matricula}
        onChange={(e) => setFormData({...formData, matricula: e.target.value})}
        placeholder="Matrícula"
        required
        disabled={!!aluno}
      />
      <input
        name="cpf"
        value={formData.cpf}
        onChange={(e) => setFormData({...formData, cpf: e.target.value})}
        placeholder="CPF"
        required
      />
      <input
        name="pnome"
        value={formData.pnome}
        onChange={(e) => setFormData({...formData, pnome: e.target.value})}
        placeholder="Primeiro Nome"
        required
      />
      <input
        name="snome"
        value={formData.snome}
        onChange={(e) => setFormData({...formData, snome: e.target.value})}
        placeholder="Sobrenome"
        required
      />
      <input
        name="idade"
        type="number"
        value={formData.idade}
        onChange={(e) => setFormData({...formData, idade: e.target.value})}
        placeholder="Idade"
        required
      />
      <button type="submit">Salvar</button>
      {aluno && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default AlunoForm;