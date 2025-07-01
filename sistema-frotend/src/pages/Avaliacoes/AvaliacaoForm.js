import React, { useState } from 'react';
import { createAvaliacao, updateAvaliacao } from '../../services/AvaliacaoService';

const AvaliacaoForm = ({ avaliacao, onSave, onCancel }) => {
  const [formData, setFormData] = useState(avaliacao || {
    titulo: '',
    data: '',
    turmaCodigo: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (avaliacao) {
      await updateAvaliacao(avaliacao.id, formData);
    } else {
      await createAvaliacao(formData);
    }
    onSave();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="titulo"
        value={formData.titulo}
        onChange={(e) => setFormData({...formData, titulo: e.target.value})}
        placeholder="Título"
        required
      />
      <input
        type="date"
        name="data"
        value={formData.data}
        onChange={(e) => setFormData({...formData, data: e.target.value})}
        required
      />
      <button type="submit">Salvar</button>
      {avaliacao && <button type="button" onClick={onCancel}>Cancelar</button>}
    </form>
  );
};

export default AvaliacaoForm;