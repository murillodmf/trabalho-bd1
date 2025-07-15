import React, { useState } from 'react';
import { createAlunoTurma } from '../../services/AlunoTurmaService';

const AlunoTurmaForm = ({ onSave, turmas, alunos }) => {
  const [formData, setFormData] = useState({
    matricula: '',
    codTurma: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.matricula || !formData.codTurma) {
      alert("Por favor, selecione um aluno e uma turma.");
      return;
    }
    await createAlunoTurma(formData);
    onSave();
    setFormData({ matricula: '', codTurma: '' });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Matricular Aluno em Turma</h2>
      <div className="form-group">
        <label htmlFor="aluno">Aluno:</label>
        <select
          id="aluno"
          name="matricula"
          value={formData.matricula}
          onChange={(e) => setFormData({ ...formData, matricula: e.target.value })}
          required
        >
          <option value="">Selecione um aluno</option>
          {alunos.map(aluno => (
            <option key={aluno.matricula} value={aluno.matricula}>
              {aluno.pnome} {aluno.snome} ({aluno.matricula})
            </option>
          ))}
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="turma">Turma:</label>
        <select
          id="turma"
          name="codTurma"
          value={formData.codTurma}
          onChange={(e) => setFormData({ ...formData, codTurma: e.target.value })}
          required
        >
          <option value="">Selecione uma turma</option>
          {turmas.map(turma => (
            <option key={turma.cod} value={turma.cod}>
              {turma.materia} ({turma.cod})
            </option>
          ))}
        </select>
      </div>

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">Matricular</button>
      </div>
    </form>
  );
};

export default AlunoTurmaForm;