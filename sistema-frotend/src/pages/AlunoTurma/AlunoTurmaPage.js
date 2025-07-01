import React, { useState, useEffect } from 'react';
import { getAlunoTurmas, deleteAlunoTurma } from '../../services/AlunoTurmaService';
import { getTurmas } from '../../services/TurmaService';
import { getAlunos } from '../../services/AlunoService';
import AlunoTurmaForm from './AlunoTurmaForm';

const AlunoTurmaPage = () => {
  const [alunoTurmas, setAlunoTurmas] = useState([]);
  const [turmas, setTurmas] = useState([]);
  const [alunos, setAlunos] = useState([]);
  const [selectedTurma, setSelectedTurma] = useState(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    carregarDados();
  }, []);

const carregarDados = async () => {
  try {
    const [alunoTurmasRes, turmasRes, alunosRes] = await Promise.all([
      getAlunoTurmas().catch(() => ({ data: [] })), 
      getTurmas().catch(() => ({ data: [] })),
      getAlunos().catch(() => ({ data: [] }))
    ]);
    
    setAlunoTurmas(alunoTurmasRes.data || []);
    setTurmas(turmasRes.data || []);
    setAlunos(alunosRes.data || []);
  } catch (error) {
    console.error("Erro ao carregar dados:", error);
  }
};

  const handleDelete = async (matricula, codTurma) => {
    await deleteAlunoTurma(matricula, codTurma);
    carregarDados();
  };

  const handleTurmaClick = (turma) => {
    setSelectedTurma(turma);
    setShowModal(true);
  };

  const getAlunosNaTurma = (codTurma) => {
    return alunoTurmas
      .filter(at => at.turma.codigo === codTurma)
      .map(at => alunos.find(a => a.matricula === at.aluno.matricula))
      .filter(Boolean);
  };

  return (
    <div>
      <h1>Alunos em Turmas</h1>
      <AlunoTurmaForm 
        onSave={carregarDados} 
        turmas={turmas} 
        alunos={alunos} 
      />
      
      <h2>Lista de Turmas</h2>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
        {turmas.map((turma) => (
          <div 
            key={turma.codigo}
            style={{
              border: '1px solid #ccc',
              padding: '10px',
              borderRadius: '5px',
              cursor: 'pointer',
              width: '200px'
            }}
            onClick={() => handleTurmaClick(turma)}
          >
            <h3>{turma.disciplina}</h3>
            <p>Código: {turma.codigo}</p>
            <p>Alunos: {getAlunosNaTurma(turma.codigo).length}</p>
          </div>
        ))}
      </div>

      {/* Modal para mostrar alunos da turma */}
      {showModal && selectedTurma && (
        <div style={{
          position: 'fixed',
          top: '0',
          left: '0',
          right: '0',
          bottom: '0',
          backgroundColor: 'rgba(0,0,0,0.5)',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          zIndex: '1000'
        }}>
          <div style={{
            backgroundColor: 'white',
            padding: '20px',
            borderRadius: '5px',
            width: '400px',
            maxHeight: '80vh',
            overflowY: 'auto'
          }}>
            <h2>Alunos na Turma {selectedTurma.disciplina}</h2>
            <button 
              onClick={() => setShowModal(false)}
              style={{ float: 'right', marginBottom: '10px' }}
            >
              Fechar
            </button>
            <table style={{ width: '100%' }}>
              <thead>
                <tr>
                  <th>Matrícula</th>
                  <th>Nome</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                {getAlunosNaTurma(selectedTurma.codigo).map(aluno => (
  <tr key={aluno.matricula}>
    <td>{aluno.matricula}</td>
    <td>{aluno.nomeCompleto}</td>
    <td>
      <button 
        onClick={() => {
          handleDelete(aluno.matricula, selectedTurma.codigo);
          setShowModal(false);
        }}
      >
        Remover
      </button>
    </td>
  </tr>
))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default AlunoTurmaPage;