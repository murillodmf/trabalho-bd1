import React, { useState, useEffect } from 'react';

const QuestaoForm = ({ questaoParaEditar, onSave, onCancel }) => {
  const [formData, setFormData] = useState({
    enunciado: '',
    tipo: 'OBJETIVA',
    respostaCorreta: '',
    respostaModelo: '',
    alternativas: ['']
  });

  useEffect(() => {
    if (questaoParaEditar) {
      setFormData({
        idQuestao: questaoParaEditar.idQuestao,
        enunciado: questaoParaEditar.enunciado,
        tipo: questaoParaEditar.tipo,
        respostaCorreta: questaoParaEditar.respostaCorreta || '',
        respostaModelo: questaoParaEditar.respostaModelo || '',
        alternativas: questaoParaEditar.alternativas && questaoParaEditar.alternativas.length > 0
          ? questaoParaEditar.alternativas
          : ['']
      });
    } else {
      resetForm();
    }
  }, [questaoParaEditar]);

  const resetForm = () => {
    setFormData({
      enunciado: '',
      tipo: 'OBJETIVA',
      respostaCorreta: '',
      respostaModelo: '',
      alternativas: ['']
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleAlternativaChange = (index, value) => {
    const novasAlternativas = [...formData.alternativas];
    novasAlternativas[index] = value;
    setFormData({ ...formData, alternativas: novasAlternativas });
  };

  const addAlternativa = () => {
    setFormData({ ...formData, alternativas: [...formData.alternativas, ''] });
  };

  const removeAlternativa = (index) => {
    const novasAlternativas = formData.alternativas.filter((_, i) => i !== index);
    setFormData({ ...formData, alternativas: novasAlternativas });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const dataToSend = { ...formData };
    if (dataToSend.tipo === 'OBJETIVA') {
      delete dataToSend.respostaModelo;
      dataToSend.alternativas = dataToSend.alternativas.filter(alt => alt.trim() !== '');
    } else {
      delete dataToSend.respostaCorreta;
      delete dataToSend.alternativas;
    }
    onSave(dataToSend);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>{questaoParaEditar ? 'Editar Questão' : 'Nova Questão'}</h3>
      <div className="form-group">
        <label htmlFor="tipo">Tipo:</label>
        <select
          id="tipo"
          name="tipo"
          value={formData.tipo}
          onChange={handleInputChange}
          required
        >
          <option value="OBJETIVA">Objetiva</option>
          <option value="DISSERTATIVA">Dissertativa</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="enunciado">Enunciado:</label>
        <textarea
          id="enunciado"
          name="enunciado"
          value={formData.enunciado}
          onChange={handleInputChange}
          placeholder="Digite o enunciado da questão..."
          required
          rows={4}
        />
      </div>

      {formData.tipo === 'OBJETIVA' ? (
        <>
          <div className="form-group">
            <label htmlFor="respostaCorreta">Resposta Correta:</label>
            <input
              type="text"
              id="respostaCorreta"
              name="respostaCorreta"
              value={formData.respostaCorreta}
              onChange={handleInputChange}
              placeholder="Digite a resposta correta..."
              required
            />
          </div>

          <div className="form-group">
            <label>Alternativas:</label>
            {formData.alternativas.map((alt, index) => (
              <div key={index} className="alternativa-item">
                <input
                  type="text"
                  value={alt}
                  onChange={(e) => handleAlternativaChange(index, e.target.value)}
                  placeholder={`Alternativa ${index + 1}`}
                  required
                />
                {formData.alternativas.length > 1 && (
                  <button
                    type="button"
                    onClick={() => removeAlternativa(index)}
                    className="btn btn-danger"
                  >
                    Remover
                  </button>
                )}
              </div>
            ))}
            <button type="button" onClick={addAlternativa} className="btn-add-item">
              Adicionar Alternativa
            </button>
          </div>
        </>
      ) : (
        <div className="form-group">
          <label htmlFor="respostaModelo">Resposta Modelo:</label>
          <textarea
            id="respostaModelo"
            name="respostaModelo"
            value={formData.respostaModelo}
            onChange={handleInputChange}
            placeholder="Digite a resposta modelo para a questão dissertativa..."
            rows={4}
          />
        </div>
      )}

      <div className="form-actions">
        <button type="submit" className="btn btn-primary">
          {questaoParaEditar ? 'Atualizar Questão' : 'Criar Questão'}
        </button>
        {questaoParaEditar && (
          <button type="button" onClick={onCancel} className="btn btn-secondary">
            Cancelar
          </button>
        )}
      </div>
    </form>
  );
};

export default QuestaoForm;