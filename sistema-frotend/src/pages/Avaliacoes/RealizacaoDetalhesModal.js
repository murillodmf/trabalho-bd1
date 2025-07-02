import React, { useState } from 'react';

const RealizacaoDetalhesModal = ({ avaliacao, realizacoes, loading, error, onClose, onSaveRealizacao }) => {
  const [editMode, setEditMode] = useState({});
  const [currentRealizacoes, setCurrentRealizacoes] = useState(realizacoes);

  React.useEffect(() => {
    setCurrentRealizacoes(realizacoes);
  }, [realizacoes]);

  const handleEditClick = (realizacao) => {
    const key = `${realizacao.idProva}_${realizacao.idQuestao}_${realizacao.matriculaAluno}`;
    setEditMode(prev => ({ ...prev, [key]: true }));
  };

  const handleCancelEdit = (realizacao) => {
    const key = `${realizacao.idProva}_${realizacao.idQuestao}_${realizacao.matriculaAluno}`;
    setEditMode(prev => ({ ...prev, [key]: false }));
  };

  const handleInputChange = (e, realizacaoToUpdate) => {
    const { name, value } = e.target;
    setCurrentRealizacoes(prev =>
      prev.map(r =>
        (r.idProva === realizacaoToUpdate.idProva &&
         r.idQuestao === realizacaoToUpdate.idQuestao &&
         r.matriculaAluno === realizacaoToUpdate.matriculaAluno)
          ? { ...r, [name]: (name === 'nota' ? parseFloat(value) : value) }
          : r
      )
    );
  };

  const handleSaveClick = (realizacao) => {
    onSaveRealizacao(realizacao);
    handleCancelEdit(realizacao);
  };

  const realizacoesPorAluno = currentRealizacoes.reduce((acc, current) => {
    const alunoKey = `${current.matriculaAluno}-${current.nomeAluno}`;
    if (!acc[alunoKey]) {
      acc[alunoKey] = {
        matricula: current.matriculaAluno,
        nome: current.nomeAluno,
        questoesRealizadas: []
      };
    }
    acc[alunoKey].questoesRealizadas.push(current);
    return acc;
  }, {});

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <div className="modal-header">
          <h2>Detalhes da Avaliação: {avaliacao.idProva}</h2>
          <button onClick={onClose} className="modal-close-btn">&times;</button>
        </div>
        <div className="modal-body">
          <p><strong>Data:</strong> {new Date(avaliacao.data).toLocaleDateString('pt-BR')}</p>
          <p><strong>Nota Máxima:</strong> {avaliacao.notaMaxima.toFixed(2)}</p>
          <p><strong>Turma:</strong> {avaliacao.nomeTurma || 'N/A'}</p>

          <h3>Realizações por Aluno</h3>
          {loading ? (
            <p>Carregando realizações...</p>
          ) : error ? (
            <div className="error-message">{error}</div>
          ) : Object.keys(realizacoesPorAluno).length === 0 ? (
            <p>Nenhum aluno realizou esta avaliação ou nenhuma questão foi associada.</p>
          ) : (
            <div>
              {Object.values(realizacoesPorAluno).map(alunoData => (
                <div key={alunoData.matricula} className="realizacao-aluno-group" style={{marginBottom: '20px', border: '1px solid #ddd', borderRadius: '5px', padding: '15px', backgroundColor: '#fdfdfd'}}>
                  <h4>Aluno: {alunoData.nome} (Matrícula: {alunoData.matricula})</h4>
                  {alunoData.questoesRealizadas.length === 0 ? (
                      <p>Este aluno não realizou nenhuma questão desta prova.</p>
                  ) : (
                      <div className="realizacoes-grid">
                          {alunoData.questoesRealizadas.map(realizacao => {
                              const key = `${realizacao.idProva}_${realizacao.idQuestao}_${realizacao.matriculaAluno}`;
                              const isEditing = editMode[key];
                              return (
                                  <div key={key} className="realizacao-card">
                                      <p><strong>Questão ID:</strong> {realizacao.idQuestao} ({realizacao.tipoQuestao})</p>
                                      <p><strong>Enunciado:</strong> {realizacao.enunciadoQuestao}</p>

                                      <div className="form-group">
                                          <label>Nota:</label>
                                          {isEditing ? (
                                              <input
                                                  type="number"
                                                  name="nota"
                                                  value={realizacao.nota || ''}
                                                  onChange={(e) => handleInputChange(e, realizacao)}
                                                  step="0.01"
                                                  min="0"
                                                  max={avaliacao.notaMaxima}
                                                  className="correction-input"
                                              />
                                          ) : (
                                              <p>{realizacao.nota != null ? realizacao.nota.toFixed(2) : 'N/A'}</p>
                                          )}
                                      </div>

                                      <div className="form-group">
                                          <label>Comentário:</label>
                                          {isEditing ? (
                                              <textarea
                                                  name="comentario"
                                                  value={realizacao.comentario || ''}
                                                  onChange={(e) => handleInputChange(e, realizacao)}
                                                  className="correction-input"
                                              />
                                          ) : (
                                              <p>{realizacao.comentario || 'Nenhum comentário.'}</p>
                                          )}
                                      </div>

                                      <div className="correction-actions">
                                          {isEditing ? (
                                              <>
                                                  <button onClick={() => handleSaveClick(realizacao)} className="btn-primary">Salvar</button>
                                                  <button onClick={() => handleCancelEdit(realizacao)} className="btn-secondary">Cancelar</button>
                                              </>
                                          ) : (
                                              <button onClick={() => handleEditClick(realizacao)} className="btn-warning">Editar Nota/Comentário</button>
                                          )}
                                      </div>
                                  </div>
                              );
                          })}
                      </div>
                  )}
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default RealizacaoDetalhesModal;
