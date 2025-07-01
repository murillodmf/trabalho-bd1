import React, { useState } from 'react';
import { createTurma, updateTurma } from '../../services/TurmaService';

const TurmaForm = ({ turma, onSave, onCancel }) => {
  const [formData, setFormData] = useState(turma || {
    cod: '',
    materia: '',
    quantidadealunos: 0,
    registro: ''
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
        name="quantidadealunos"
        type="number"
        value={formData.quantidadealunos}
        onChange={(e) => setFormData({...formData, quantidadealunos: parseInt(e.target.value) || 0})}
        placeholder="Quantidade de Alunos"
        required
      />
      <input
        name="registro"
        value={formData.registro}
        onChange={(e) => setFormData({...formData, registro: e.target.value})}
        placeholder="Registro do Professor"
        required
      />
      <button type="submit">Salvar</button>
      {turma && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default TurmaForm;