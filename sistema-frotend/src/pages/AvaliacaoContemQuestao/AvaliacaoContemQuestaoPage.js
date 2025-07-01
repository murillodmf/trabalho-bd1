import React, { useState, useEffect } from 'react';
import { createAvaliacaoQuestao, deleteByProva, deleteByQuestao } from '../../services/AvaliacaoContemQuestaoService';

const AvaliacaoContemQuestaoPage = () => {
  const [relacoes, setRelacoes] = useState([]);
  const [novaRelacao, setNovaRelacao] = useState({
    idProva: '',
    idQuestao: ''
  });

  const carregarRelacoes = async () => {
    // Implemente essa função se tiver um endpoint para listar
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createAvaliacaoQuestao(novaRelacao);
    setNovaRelacao({ idProva: '', idQuestao: '' });
    carregarRelacoes();
  };

  return (
    <div>
      <h1>Relação Avaliação-Questão</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="ID Prova"
          value={novaRelacao.idProva}
          onChange={(e) => setNovaRelacao({...novaRelacao, idProva: e.target.value})}
        />
        <input
          type="number"
          placeholder="ID Questão"
          value={novaRelacao.idQuestao}
          onChange={(e) => setNovaRelacao({...novaRelacao, idQuestao: e.target.value})}
        />
        <button type="submit">Adicionar</button>
      </form>
      <div>
        <h3>Remover relações:</h3>
        <button onClick={() => deleteByProva(novaRelacao.idProva)}>
          Remover por Prova
        </button>
        <button onClick={() => deleteByQuestao(novaRelacao.idQuestao)}>
          Remover por Questão
        </button>
      </div>
    </div>
  );
};

export default AvaliacaoContemQuestaoPage;