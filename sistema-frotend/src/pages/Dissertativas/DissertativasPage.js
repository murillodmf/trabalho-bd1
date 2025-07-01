import React, { useState, useEffect } from 'react';
import { createDissertativa, updateDissertativa, deleteDissertativa } from '../../services/DissertativaService';

const DissertativasPage = () => {
  const [dissertativas, setDissertativas] = useState([]);
  const [formData, setFormData] = useState({
    idQuestao: '',
    criteriosCorrecao: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.idQuestao) {
      await updateDissertativa(formData.idQuestao, formData);
    } else {
      await createDissertativa(formData);
    }
    setFormData({ idQuestao: '', criteriosCorrecao: '' });
  };

  return (
    <div>
      <h1>Questões Dissertativas</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="ID Questão"
          value={formData.idQuestao}
          onChange={(e) => setFormData({...formData, idQuestao: e.target.value})}
        />
        <textarea
          placeholder="Critérios de Correção"
          value={formData.criteriosCorrecao}
          onChange={(e) => setFormData({...formData, criteriosCorrecao: e.target.value})}
        />
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
};

export default DissertativasPage;