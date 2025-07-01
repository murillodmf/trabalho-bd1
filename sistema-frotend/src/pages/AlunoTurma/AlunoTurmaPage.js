import React, { useState, useEffect } from 'react';
import { getAlunoTurmas, deleteAlunoTurma } from '../../services/AlunoTurmaService';
import { getTurmas } from '../../services/TurmaService';
import { getAlunos } from '../../services/AlunoService';
import AlunoTurmaForm from './AlunoTurmaForm';

const AlunoTurmaPage = () => {
  const [alunoTurmas, setAlunoTurmas] = useState([]);
  const [turmas, setTurmas] = useState([]);
  const [alunos, setAlunos] = useState([]);
  const [alunosMap, setAlunosMap] = useState({});
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

      // Monta o mapa para acesso rápido por matrícula
      const map = {};
      (alunosRes.data || []).forEach(aluno => {
        map[aluno.matricula] = aluno;
      });
      setAlunosMap(map);
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
      .filter(at => at.turma.cod === codTurma)
      .map(at => {
        const aluno = alunosMap[at.aluno.matricula];
        return aluno ? {
          ...aluno,
          nomeCompleto: `${aluno.pnome} ${aluno.snome}`
        } : null;
      })
      .filter(Boolean);
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1 style={{ marginBottom: '20px' }}>Alunos em Turmas</h1>

      <AlunoTurmaForm
        onSave={carregarDados}
        turmas={turmas}
        alunos={alunos}
      />

      <h2 style={{ margin: '20px 0 10px 0' }}>Lista de Turmas</h2>

      {turmas.length === 0 ? (
        <p>Nenhuma turma cadastrada</p>
      ) : (
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
          gap: '15px',
          marginTop: '15px'
        }}>
          {turmas.map((turma) => (
            <div
              key={turma.cod}
              style={{
                border: '1px solid #ddd',
                borderRadius: '8px',
                padding: '15px',
                cursor: 'pointer',
                transition: 'all 0.3s ease'
              }}
              onClick={() => handleTurmaClick(turma)}
            >
              <h3 style={{ marginTop: 0, color: '#333' }}>{turma.materia}</h3>
              <p><strong>Código:</strong> {turma.cod}</p>
              <p><strong>Alunos matriculados:</strong> {turma.quantidadeAlunos}</p>
            </div>
          ))}
        </div>
      )}

      {/* Modal de alunos matriculados */}
      {showModal && selectedTurma && (
        <div style={{
          position: 'fixed',
          top: 0, left: 0, right: 0, bottom: 0,
          backgroundColor: 'rgba(0,0,0,0.5)',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          zIndex: 1000
        }}>
          <div style={{
            backgroundColor: 'white',
            borderRadius: '8px',
            width: '90%',
            maxWidth: '600px',
            maxHeight: '80vh',
            overflow: 'hidden',
            boxShadow: '0 4px 20px rgba(0,0,0,0.15)'
          }}>
            <div style={{
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              padding: '15px 20px',
              borderBottom: '1px solid #eee',
              backgroundColor: '#f8f9fa'
            }}>
              <h2 style={{ margin: 0 }}>
                Alunos na Turma: {selectedTurma.materia} ({selectedTurma.cod})
              </h2>
              <button
                onClick={() => setShowModal(false)}
                style={{
                  background: 'none',
                  border: 'none',
                  fontSize: '1.5rem',
                  cursor: 'pointer',
                  color: '#666'
                }}
                aria-label="Fechar modal"
              >
                &times;
              </button>
            </div>

            <div style={{ padding: '20px', overflowY: 'auto', maxHeight: 'calc(80vh - 60px)' }}>
              {getAlunosNaTurma(selectedTurma.cod).length === 0 ? (
                <p style={{ textAlign: 'center', color: '#666' }}>
                  Nenhum aluno matriculado nesta turma
                </p>
              ) : (
                <table style={{
                  width: '100%',
                  borderCollapse: 'collapse',
                  marginTop: '10px'
                }}>
                  <thead>
                    <tr style={{
                      backgroundColor: '#f1f1f1',
                      textAlign: 'left'
                    }}>
                      <th style={{ padding: '12px 15px' }}>Matrícula</th>
                      <th style={{ padding: '12px 15px' }}>Nome</th>
                      <th style={{ padding: '12px 15px' }}>Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    {getAlunosNaTurma(selectedTurma.cod).map(aluno => (
                      <tr key={aluno.matricula} style={{ borderBottom: '1px solid #eee' }}>
                        <td style={{ padding: '12px 15px' }}>{aluno.matricula}</td>
                        <td style={{ padding: '12px 15px' }}>{aluno.pnome} {aluno.snome}</td>
                        <td style={{ padding: '12px 15px' }}>
                          <button
                            onClick={() => {
                              handleDelete(aluno.matricula, selectedTurma.cod);
                              setShowModal(false);
                            }}
                            style={{
                              padding: '6px 12px',
                              backgroundColor: '#ff4444',
                              color: 'white',
                              border: 'none',
                              borderRadius: '4px',
                              cursor: 'pointer',
                              transition: 'background-color 0.2s'
                            }}
                          >
                            Remover
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default AlunoTurmaPage;
