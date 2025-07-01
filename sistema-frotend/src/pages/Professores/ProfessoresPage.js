import React, { useState, useEffect } from 'react';
import { getProfessores, deleteProfessor } from '../../services/ProfessorService';
import ProfessorForm from './ProfessorForm';

const ProfessoresPage = () => {
  const [professores, setProfessores] = useState([]);
  const [professorEditando, setProfessorEditando] = useState(null);

  useEffect(() => {
    carregarProfessores();
  }, []);

  const carregarProfessores = async () => {
    try {
      const response = await getProfessores();
      setProfessores(response.data);
    } catch (error) {
      console.error("Erro ao carregar professores:", error);
    }
  };

  const handleDelete = async (registro) => {
    if (window.confirm('Tem certeza que deseja excluir este professor?')) {
      await deleteProfessor(registro);
      carregarProfessores();
    }
  };

  return (
    <div className="container">
      <h1>Professores</h1>
      <ProfessorForm 
        professor={professorEditando} 
        onSave={() => {
          setProfessorEditando(null);
          carregarProfessores();
        }} 
        onCancel={() => setProfessorEditando(null)}
      />

      <table className="professores-table">
        <thead>
          <tr>
            <th>Registro</th>
            <th>Nome</th>
            <th>Sobrenome</th>
            <th>CPF</th>
            <th>Idade</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {professores.map((professor) => (
            <tr key={professor.registro}>
              <td>{professor.registro}</td>
              <td>{professor.pnome}</td>
              <td>{professor.snome}</td>
              <td>{professor.cpf}</td>
              <td>{professor.idade}</td>
              <td>
                <button onClick={() => setProfessorEditando(professor)}>Editar</button>
                <button onClick={() => handleDelete(professor.registro)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ProfessoresPage;