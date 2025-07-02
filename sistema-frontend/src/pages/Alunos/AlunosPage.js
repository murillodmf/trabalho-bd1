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

        <h2>Lista de Alunos</h2>
        <table className="data-table">
          <thead>
          <tr>
            <th>Matrícula</th>
            <th>CPF</th>
            <th>Nome Completo</th>
            <th>Idade</th>
            <th>Ações</th>
          </tr>
          </thead>
          <tbody>
          {alunos.map((aluno) => (
              <tr key={aluno.matricula}>
                <td>{aluno.matricula}</td>
                <td>{aluno.cpf}</td>
                <td>{`${aluno.pnome} ${aluno.snome || ''}`}</td>
                <td>{aluno.idade}</td>
                <td>
                  <button className="btn btn-edit" onClick={() => setAlunoEditando(aluno)}>Editar</button>
                  <button className="btn btn-danger" onClick={() => handleDelete(aluno.matricula)}>Excluir</button>
                </td>
              </tr>
          ))}
          </tbody>
        </table>
      </div>
  );
};

export default AlunosPage;