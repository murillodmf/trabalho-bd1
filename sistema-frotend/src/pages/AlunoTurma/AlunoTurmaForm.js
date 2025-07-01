import React, { useState } from 'react';
import { createAlunoTurma } from '../../services/AlunoTurmaService';

const AlunoTurmaForm = ({ onSave, turmas, alunos }) => {
  const [formData, setFormData] = useState({
    matricula: '',
    codTurma: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createAlunoTurma(formData);
    onSave();
    setFormData({ matricula: '', codTurma: '' });
  };

  return (
    <form onSubmit={handleSubmit} style={{ margin: '20px 0' }}>
      <h2>Matricular Aluno em Turma</h2>
      <div style={{ marginBottom: '10px' }}>
        <label htmlFor="aluno">Aluno: </label>
        <select
        id="aluno"
        name="matricula"
        value={formData.matricula}
        onChange={(e) => setFormData({...formData, matricula: e.target.value})}
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
      
      <div style={{ marginBottom: '10px' }}>
        <label htmlFor="turma">Turma: </label>
        <select
        id="turma"
        name="codTurma"
        value={formData.codTurma}
        onChange={(e) => setFormData({...formData, codTurma: e.target.value})}
        required
      >
        <option value="">Selecione uma turma</option>
        {turmas.map(turma => (
          <option key={turma.cod} value={turma.cod}>
            {turma.disciplina} ({turma.cod})
          </option>
        ))}
      </select>
      </div>
      
      <button type="submit">Matricular</button>
    </form>
  );
};

export default AlunoTurmaForm;