import React, { useState } from 'react';
import { createQuestao, updateQuestao } from '../../services/QuestaoService';

const QuestaoForm = ({ questao, onSave, onCancel }) => {
  const [formData, setFormData] = useState(questao || {
    enunciado: '',
    tipo: 'OBJETIVA' // ou 'DISSERTATIVA'
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (questao) {
      await updateQuestao(questao.id, formData);
    } else {
      await createQuestao(formData);
    }
    onSave();
  };

  return (
    <form onSubmit={handleSubmit}>
      <select
        name="tipo"
        value={formData.tipo}
        onChange={(e) => setFormData({...formData, tipo: e.target.value})}
      >
        <option value="OBJETIVA">Objetiva</option>
        <option value="DISSERTATIVA">Dissertativa</option>
      </select>
      <textarea
        name="enunciado"
        value={formData.enunciado}
        onChange={(e) => setFormData({...formData, enunciado: e.target.value})}
        placeholder="Enunciado"
        required
      />
      <button type="submit">Salvar</button>
      {questao && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default QuestaoForm;