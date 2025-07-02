import React from 'react';
import './HomePage.css';

const HomePage = () => {
  return (
    <div className="home-container">
      <h1>Bem-vindo ao Sistema de Avaliação</h1>
      <p>Gerencie alunos, professores, turmas e avaliações de forma eficiente</p>
      <div className="features">
        <div className="feature-card">
          <h3>Alunos</h3>
          <p>Cadastre e gerencie alunos</p>
          <a href="/alunos" className="feature-link">Ver Alunos</a>
        </div>
        <div className="feature-card">
          <h3>Professores</h3>
          <p>Controle o corpo docente</p>
          <a href="/professores" className="feature-link">Ver Professores</a>
        </div>
        <div className="feature-card">
          <h3>Turmas</h3>
          <p>Organize as turmas e disciplinas</p>
          <a href="/turmas" className="feature-link">Ver Turmas</a>
        </div>
      </div>
    </div>
  );
};

export default HomePage;