import React, { useState } from 'react';
import { createRealizacao } from '../../services/RealizaProvaService';

const RealizaProvaForm = ({ onSave }) => {
  const [formData, setFormData] = useState({
    idProva: '',
    idQuestao: '',
    matricula: '',
    resposta: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createRealizacao(formData);
    onSave();
    setFormData({
      idProva: '',
      idQuestao: '',
      matricula: '',
      resposta: ''
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="idProva"
        value={formData.idProva}
        onChange={(e) => setFormData({...formData, idProva: e.target.value})}
        placeholder="ID Prova"
        required
      />
      <input
        name="idQuestao"
        value={formData.idQuestao}
        onChange={(e) => setFormData({...formData, idQuestao: e.target.value})}
        placeholder="ID Questão"
        required
      />
      <input
        name="matricula"
        value={formData.matricula}
        onChange={(e) => setFormData({...formData, matricula: e.target.value})}
        placeholder="Matrícula"
        required
      />
      <textarea
        name="resposta"
        value={formData.resposta}
        onChange={(e) => setFormData({...formData, resposta: e.target.value})}
        placeholder="Resposta"
        required
      />
      <button type="submit">Registrar Resposta</button>
    </form>
  );
};

export default RealizaProvaForm;