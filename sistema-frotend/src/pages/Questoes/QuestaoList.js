import React, { useState, useEffect } from 'react';
import api from '../../api/api';


const QuestaoForm = ({ questao, onSave, onCancel }) => {
  const [formData, setFormData] = useState({
    enunciado: '',
    tipo: 'OBJETIVA',
    respostaCorreta: '',
    respostaModelo: '',
    alternativas: ['']
  });

  useEffect(() => {
    if (questao) {
      carregarDadosQuestao(questao);
    } else {
      resetForm();
    }
  }, [questao]);

  const carregarDadosQuestao = async (questao) => {
    const dadosBase = {
      enunciado: questao.enunciado,
      tipo: questao.tipo
    };

    if (questao.tipo === 'OBJETIVA') {
      try {
        const [respostaRes, alternativasRes] = await Promise.all([
          api.get(`/objetivas/${questao.idQuestao}`),
          api.get(`/objetivas/alternativas/${questao.idQuestao}`)
        ]);

        setFormData({
          ...dadosBase,
          respostaCorreta: respostaRes.data.resposta,
          respostaModelo: '',
          alternativas: alternativasRes.data.map(a => a.alternativa)
        });
      } catch (error) {
        console.error('Erro ao carregar dados da questão objetiva:', error);
      }
    } else {
      try {
        const respostaRes = await api.get(`/dissertativas/${questao.idQuestao}`);
        setFormData({
          ...dadosBase,
          respostaCorreta: '',
          respostaModelo: respostaRes.data.respostaModelo,
          alternativas: ['']
        });
      } catch (error) {
        console.error('Erro ao carregar dados da questão dissertativa:', error);
      }
    }
  };

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
    onSave(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="questao-form">
      <div className="form-group">
        <label>Tipo:</label>
        <select 
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
        <label>Enunciado:</label>
        <textarea
          name="enunciado"
          value={formData.enunciado}
          onChange={handleInputChange}
          required
        />
      </div>
      
      {formData.tipo === 'OBJETIVA' ? (
        <>
          <div className="form-group">
            <label>Resposta Correta:</label>
            <input
              type="text"
              name="respostaCorreta"
              value={formData.respostaCorreta}
              onChange={handleInputChange}
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
                  required
                />
                <button 
                  type="button" 
                  onClick={() => removeAlternativa(index)}
                  disabled={formData.alternativas.length <= 1}
                >
                  Remover
                </button>
              </div>
            ))}
            <button type="button" onClick={addAlternativa}>
              Adicionar Alternativa
            </button>
          </div>
        </>
      ) : (
        <div className="form-group">
          <label>Resposta Modelo:</label>
          <textarea
            name="respostaModelo"
            value={formData.respostaModelo}
            onChange={handleInputChange}
          />
        </div>
      )}
      
      <div className="form-actions">
        <button type="submit" className="btn-save">
          {questao ? 'Atualizar' : 'Salvar'}
        </button>
        {questao && (
          <button type="button" onClick={onCancel} className="btn-cancel">
            Cancelar
          </button>
        )}
      </div>
    </form>
  );
};

export default QuestaoForm;