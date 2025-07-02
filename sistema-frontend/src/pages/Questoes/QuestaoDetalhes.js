import React from 'react';

const QuestaoDetalhes = ({ questao, onClose }) => {
  if (!questao) {
    return null;
  }

  return (
    <div style={modalOverlayStyle}>
      <div style={modalContentStyle}>
        <div style={modalHeaderStyle}>
          <h2>Detalhes da Questão</h2>
          <button onClick={onClose} style={closeButtonStyle}>&times;</button>
        </div>
        <div style={modalBodyStyle}>
          <p><strong>ID:</strong> {questao.idQuestao}</p>
          <p><strong>Tipo:</strong> {questao.tipo === 'OBJETIVA' ? 'Objetiva' : 'Dissertativa'}</p>
          <p><strong>Enunciado:</strong> {questao.enunciado}</p>

          {questao.tipo === 'OBJETIVA' && (
            <>
              <p><strong>Resposta Correta:</strong> {questao.respostaCorreta}</p>
              <h4>Alternativas:</h4>
              {questao.alternativas && questao.alternativas.length > 0 ? (
                <ul style={listStyle}>
                  {questao.alternativas.map((alt, index) => (
                    <li key={index}>{alt}</li>
                  ))}
                </ul>
              ) : (
                <p>Nenhuma alternativa cadastrada.</p>
              )}
            </>
          )}

          {questao.tipo === 'DISSERTATIVA' && (
            <p><strong>Resposta Modelo:</strong> {questao.respostaModelo || 'Nenhuma resposta modelo.'}</p>
          )}
        </div>
      </div>
    </div>
  );
};

const modalOverlayStyle = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(0, 0, 0, 0.5)',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  zIndex: 1000,
};

const modalContentStyle = {
  backgroundColor: 'white',
  borderRadius: '8px',
  width: '90%',
  maxWidth: '600px',
  maxHeight: '80vh',
  overflow: 'hidden',
  boxShadow: '0 4px 20px rgba(0,0,0,0.15)',
  display: 'flex',
  flexDirection: 'column',
};

const modalHeaderStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  padding: '15px 20px',
  borderBottom: '1px solid #eee',
  backgroundColor: '#f8f9fa',
};

const closeButtonStyle = {
  background: 'none',
  border: 'none',
  fontSize: '1.5rem',
  cursor: 'pointer',
  color: '#666',
};

const modalBodyStyle = {
  padding: '20px',
  overflowY: 'auto',
  flexGrow: 1,
};

const listStyle = {
  listStyleType: 'disc',
  marginLeft: '20px',
};

export default QuestaoDetalhes;