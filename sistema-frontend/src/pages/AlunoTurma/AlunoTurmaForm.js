import React, { useState } from 'react';
import { createAlunoTurma } from '../../services/AlunoTurmaService';

const AlunoTurmaForm = ({ onSave, turmas, alunos }) => {
  const [formData, setFormData] = useState({
    matricula: '',
    codTurma: ''
  });
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!formData.matricula || !formData.codTurma) {
      setError("Por favor, selecione um aluno e uma turma.");
      return;
    }

    try {
      await createAlunoTurma(formData);
      onSave();
      setFormData({ matricula: '', codTurma: '' });
    } catch (err) {
      if (err.response && err.response.status === 500) {
        setError("Este aluno já está matriculado nesta turma.");
      } else {
        setError("Ocorreu um erro ao tentar matricular o aluno.");
      }
      console.error(err);
    }
  };

  const handleChange = (e) => {
    setError('');
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Matricular Aluno em Turma</h2>
      
      {error && <p className="error-message">{error}</p>}

      <div className="form-group">
        <label htmlFor="aluno">Aluno:</label>
        <select
          id="aluno"
          name="matricula"
          value={formData.matricula}
          onChange={handleChange}
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
          onChange={handleChange}
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
