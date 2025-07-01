import React, { useState, useEffect } from 'react';
import { getAlunoTurmas, deleteAlunoTurma } from '../../services/AlunoTurmaService';
import AlunoTurmaForm from './AlunoTurmaForm';

const AlunoTurmaPage = () => {
  const [alunoTurmas, setAlunoTurmas] = useState([]);

  useEffect(() => {
    carregarAlunoTurmas();
  }, []);

  const carregarAlunoTurmas = async () => {
    const response = await getAlunoTurmas();
    setAlunoTurmas(response.data);
  };

  const handleDelete = async (matricula, codTurma) => {
    await deleteAlunoTurma(matricula, codTurma);
    carregarAlunoTurmas();
  };

  return (
    <div>
      <h1>Alunos em Turmas</h1>
      <AlunoTurmaForm onSave={carregarAlunoTurmas} />
      <table>
        <thead>
          <tr>
            <th>Aluno</th>
            <th>Turma</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {alunoTurmas.map((at) => (
            <tr key={`${at.aluno.matricula}-${at.turma.codigo}`}>
              <td>{at.aluno.nome}</td>
              <td>{at.turma.disciplina}</td>
              <td>
                <button onClick={() => handleDelete(at.aluno.matricula, at.turma.codigo)}>Remover</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AlunoTurmaPage;