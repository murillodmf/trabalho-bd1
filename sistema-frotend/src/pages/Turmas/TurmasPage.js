import React, { useState, useEffect } from 'react';
import { getTurmas, deleteTurma } from '../../services/TurmaService';
import TurmaForm from './TurmaForm';

const TurmasPage = () => {
  const [turmas, setTurmas] = useState([]);
  const [turmaEditando, setTurmaEditando] = useState(null);

  useEffect(() => {
    carregarTurmas();
  }, []);

  const carregarTurmas = async () => {
    try {
      const response = await getTurmas();
      setTurmas(response.data);
    } catch (error) {
      console.error("Erro ao carregar turmas:", error);
    }
  };

  const handleDelete = async (cod) => {
    if (window.confirm('Tem certeza que deseja excluir esta turma?')) {
      await deleteTurma(cod);
      carregarTurmas();
    }
  };

  return (
    <div className="container">
      <h1>Turmas</h1>
      <TurmaForm 
        turma={turmaEditando} 
        onSave={() => {
          setTurmaEditando(null);
          carregarTurmas();
        }} 
        onCancel={() => setTurmaEditando(null)}
      />

      <table className="turmas-table">
        <thead>
          <tr>
            <th>Código</th>
            <th>Matéria</th>
            <th>Quantidade de Alunos</th>
            <th>Registro do Professor</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {turmas.map((turma) => (
            <tr key={turma.cod}>
              <td>{turma.cod}</td>
              <td>{turma.materia}</td>
              <td>{turma.quantidadealunos}</td>
              <td>{turma.registro}</td>
              <td>
                <button onClick={() => setTurmaEditando(turma)}>Editar</button>
                <button onClick={() => handleDelete(turma.cod)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TurmasPage;