import React, { useState } from 'react';
import { createProfessor, updateProfessor } from '../../services/ProfessorService';

const ProfessorForm = ({ professor, onSave, onCancel }) => {
  const [formData, setFormData] = useState(professor || {
    registry: '',
    p_nome: '',
    s_nome: '',
    cpf: '',
    idade: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (professor) {
      await updateProfessor(professor.registry, formData);
    } else {
      await createProfessor(formData);
    }
    onSave();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="registro"
        value={formData.registry}
        onChange={(e) => setFormData({...formData, registro: e.target.value})}
        placeholder="Registro"
        required
        disabled={!!professor}
      />
      <input
        name="pnome"
        value={formData.p_nome}
        onChange={(e) => setFormData({...formData, pnome: e.target.value})}
        placeholder="Primeiro Nome"
        required
      />
      <input
        name="snome"
        value={formData.s_nome}
        onChange={(e) => setFormData({...formData, snome: e.target.value})}
        placeholder="Sobrenome"
        required
      />
      <input
        name="cpf"
        value={formData.cpf}
        onChange={(e) => setFormData({...formData, cpf: e.target.value})}
        placeholder="CPF"
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
      {professor && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default ProfessorForm;