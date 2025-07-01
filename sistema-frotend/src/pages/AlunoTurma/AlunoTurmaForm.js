import React, { useState } from 'react';
import { createAlunoTurma } from '../../services/AlunoTurmaService'; // Import corrigido

const AlunoTurmaForm = ({ onSave }) => {
  const [formData, setFormData] = useState({
    matricula: '',
    codTurma: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createAlunoTurma(formData); // Usando createAlunoTurma em vez de salvar
    onSave();
    setFormData({ matricula: '', codTurma: '' });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="matricula"
        value={formData.matricula}
        onChange={(e) => setFormData({...formData, matricula: e.target.value})}
        placeholder="Matrícula"
        required
      />
      <input
        name="codTurma"
        value={formData.codTurma}
        onChange={(e) => setFormData({...formData, codTurma: e.target.value})}
        placeholder="Código da Turma"
        required
      />
      <button type="submit">Vincular</button>
    </form>
  );
};

export default AlunoTurmaForm;