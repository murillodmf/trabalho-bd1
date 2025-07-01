import React, { useState } from 'react';
import { createObjetiva, updateObjetiva, deleteObjetiva } from '../../services/ObjetivaService';

const ObjetivasPage = () => {
  const [objetiva, setObjetiva] = useState({
    idQuestao: '',
    alternativaCorreta: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (objetiva.idQuestao) {
      await updateObjetiva(objetiva.idQuestao, objetiva);
    } else {
      await createObjetiva(objetiva);
    }
    setObjetiva({ idQuestao: '', alternativaCorreta: '' });
  };

  return (
    <div>
      <h1>Questões Objetivas</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="ID Questão"
          value={objetiva.idQuestao}
          onChange={(e) => setObjetiva({...objetiva, idQuestao: e.target.value})}
        />
        <input
          type="text"
          placeholder="Alternativa Correta"
          value={objetiva.alternativaCorreta}
          onChange={(e) => setObjetiva({...objetiva, alternativaCorreta: e.target.value})}
        />
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
};

export default ObjetivasPage;