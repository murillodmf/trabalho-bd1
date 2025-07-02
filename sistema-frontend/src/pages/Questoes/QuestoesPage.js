import React, { useState, useEffect } from 'react';
import {
  getQuestoes,
  getQuestaoCompleta,
  createQuestaoCompleta,
  updateQuestaoCompleta,
  deleteQuestao
} from '../../services/QuestaoService';
import QuestaoForm from './QuestaoForm';
import QuestaoDetalhes from './QuestaoDetalhes';
import './QuestoesPage.css';

const QuestoesPage = () => {
  const [questoes, setQuestoes] = useState([]);
  const [questaoEditando, setQuestaoEditando] = useState(null);
  const [questaoVisualizando, setQuestaoVisualizando] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarQuestoes();
  }, []);

  const carregarQuestoes = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await getQuestoes();
      setQuestoes(response.data);
    } catch (err) {
      setError("Erro ao carregar questões.");
      console.error("Erro ao carregar questões:", err);
    } finally {
      setLoading(false);
    }
  };

  const handleSave = async (formData) => {
    try {
      if (formData.idQuestao) {
        await updateQuestaoCompleta(formData.idQuestao, formData);
      } else {
        await createQuestaoCompleta(formData);
      }
      setQuestaoEditando(null);
      await carregarQuestoes();
    } catch (err) {
      setError("Erro ao salvar questão. Verifique se todos os campos estão preenchidos corretamente.");
      console.error("Erro ao salvar questão:", err);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja excluir esta questão?')) {
      try {
        await deleteQuestao(id);
        await carregarQuestoes();
      } catch (err) {
        setError("Erro ao excluir questão.");
        console.error("Erro ao excluir questão:", err);
      }
    }
  };

  const handleEditClick = async (questao) => {
    setQuestaoVisualizando(null);
    try {
      const response = await getQuestaoCompleta(questao.idQuestao);
      setQuestaoEditando(response.data);
    } catch (err) {
      setError("Erro ao carregar dados para edição.");
      console.error("Erro ao carregar dados para edição:", err);
    }
  };

  const handleViewClick = async (questao) => {
    setQuestaoEditando(null);
    try {
      const response = await getQuestaoCompleta(questao.idQuestao);
      setQuestaoVisualizando(response.data);
    } catch (err) {
      setError("Erro ao carregar detalhes da questão.");
      console.error("Erro ao carregar detalhes da questão:", err);
    }
  };

  const handleCancelEdit = () => {
    setQuestaoEditando(null);
  };

  const handleCloseDetails = () => {
    setQuestaoVisualizando(null);
  };

  return (
    <div className="questoes-page-container">
      <h1>Gerenciamento de Questões</h1>
      {error && <div className="error-message">{error}</div>}

      <div className="form-section">
        <QuestaoForm
          questaoParaEditar={questaoEditando}
          onSave={handleSave}
          onCancel={handleCancelEdit}
        />
      </div>

      <div className="list-section">
        <h2>Lista de Questões</h2>
        {loading ? (
          <p>Carregando questões...</p>
        ) : questoes.length === 0 ? (
          <p>Nenhuma questão cadastrada.</p>
        ) : (
          <div className="questoes-grid">
            {questoes.map((questao) => (
              <div
                key={questao.idQuestao}
                className="questao-card"
                onClick={() => handleViewClick(questao)}
              >
                <h3>ID: {questao.idQuestao} - {questao.tipo === 'OBJETIVA' ? 'Objetiva' : 'Dissertativa'}</h3>
                <p>{questao.enunciado.length > 100 ? `${questao.enunciado.substring(0, 100)}...` : questao.enunciado}</p>
                <div className="card-actions">
                  <button onClick={(e) => { e.stopPropagation(); handleEditClick(questao); }} className="edit-btn">
                    Editar
                  </button>
                  <button onClick={(e) => { e.stopPropagation(); handleDelete(questao.idQuestao); }} className="delete-btn">
                    Excluir
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      <QuestaoDetalhes questao={questaoVisualizando} onClose={handleCloseDetails} />
    </div>
  );
};

export default QuestoesPage;
