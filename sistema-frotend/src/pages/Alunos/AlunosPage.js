import React, { useState, useEffect } from 'react';
import { getAlunos, deleteAluno } from '../../services/AlunoService';
import AlunoForm from './AlunoForm';

const AlunosPage = () => {
  const [alunos, setAlunos] = useState([]);
  const [alunoEditando, setAlunoEditando] = useState(null);

  useEffect(() => {
    carregarAlunos();
  }, []);

  const carregarAlunos = async () => {
    try {
      const response = await getAlunos();
      setAlunos(response.data);
    } catch (error) {
      console.error("Erro ao carregar alunos:", error);
    }
  };

  const handleDelete = async (matricula) => {
    if (window.confirm('Tem certeza que deseja excluir este aluno?')) {
      await deleteAluno(matricula);
      carregarAlunos();
    }
  };

  return (
    <div className="container">
      <h1>Alunos</h1>
      <AlunoForm 
        aluno={alunoEditando} 
        onSave={() => {
          setAlunoEditando(null);
          carregarAlunos();
        }} 
        onCancel={() => setAlunoEditando(null)}
      />

      <table className="alunos-table">
        <thead>
          <tr>
            <th>Matrícula</th>
            <th>CPF</th>
            <th>Nome</th>
            <th>Sobrenome</th>
            <th>Idade</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {alunos.map((aluno) => (
            <tr key={aluno.matricula}>
              <td>{aluno.matricula}</td>
              <td>{aluno.cpf}</td>
              <td>{aluno.pnome}</td>
              <td>{aluno.snome}</td>
              <td>{aluno.idade}</td>
              <td>
                <button onClick={() => setAlunoEditando(aluno)}>Editar</button>
                <button onClick={() => handleDelete(aluno.matricula)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AlunosPage;