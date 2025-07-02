import React, { useState } from 'react';
import { createTurma, updateTurma } from '../../services/TurmaService';

const TurmaForm = ({ turma, onSave, onCancel }) => {
  const [formData, setFormData] = useState(turma || {
    cod: '',
    materia: '',
    quantidadeAlunos: 0,
    registroProfessor: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (turma) {
      await updateTurma(turma.cod, formData);
    } else {
      await createTurma(formData);
    }
    onSave();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="cod"
        type="number"
        value={formData.cod}
        onChange={(e) => setFormData({...formData, cod: e.target.value})}
        placeholder="Código da Turma"
        required
        disabled={!!turma}
      />
      <input
        name="materia"
        value={formData.materia}
        onChange={(e) => setFormData({...formData, materia: e.target.value})}
        placeholder="Matéria"
        required
      />
      <input
        name="quantidadeAlunos"
        type="number"
        value={formData.quantidadeAlunos}
        onChange={(e) => setFormData({...formData, quantidadeAlunos: e.target.value})}
        placeholder="Quantidade de Alunos"
        required
      />
      <input
        name="registroProfessor"
        type="number"
        value={formData.registroProfessor}
        onChange={(e) => setFormData({...formData, registroProfessor: e.target.value})}
        placeholder="Registro do Professor"
      />
      <button type="submit">Salvar</button>
      {turma && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default TurmaForm;