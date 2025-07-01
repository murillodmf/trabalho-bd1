import React, { useState } from 'react';
import { createObjetivaResposta, deleteResposta, deleteAllRespostas } from '../../services/ObjetivaRespostaService';

const ObjetivaRespostasPage = () => {
  const [resposta, setResposta] = useState({
    idQuestao: '',
    alternativa: '',
    correta: false
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createObjetivaResposta(resposta);
    setResposta({ idQuestao: '', alternativa: '', correta: false });
  };

  return (
    <div>
      <h1>Respostas Objetivas</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="ID Questão"
          value={resposta.idQuestao}
          onChange={(e) => setResposta({...resposta, idQuestao: e.target.value})}
        />
        <input
          type="text"
          placeholder="Alternativa (A, B, C...)"
          value={resposta.alternativa}
          onChange={(e) => setResposta({...resposta, alternativa: e.target.value})}
        />
        <label>
          <input
            type="checkbox"
            checked={resposta.correta}
            onChange={(e) => setResposta({...resposta, correta: e.target.checked})}
          />
          Resposta Correta
        </label>
        <button type="submit">Adicionar</button>
      </form>
      <div>
        <h3>Remover respostas:</h3>
        <button onClick={() => deleteResposta(resposta.idQuestao, resposta.alternativa)}>
          Remover Alternativa
        </button>
        <button onClick={() => deleteAllRespostas(resposta.idQuestao)}>
          Remover Todas
        </button>
      </div>
    </div>
  );
};

export default ObjetivaRespostasPage;