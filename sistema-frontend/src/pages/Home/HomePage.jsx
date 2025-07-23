import React from 'react';
import { Link } from 'react-router-dom';
import './HomePage.css';

const HomePage = () => {
  return (
    <div className="page-container home-container">
      <h1>Bem-vindo ao Sistema de Avaliação</h1>
      <p className="home-subtitle">Gerencie alunos, professores, turmas e avaliações de forma eficiente</p>
      
      <div className="grid-container">

        <Link to="/alunos" className="home-card-link">
          <div className="item-card home-card">
            <div className="card-icon">
              <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
            </div>
            <h3>Alunos</h3>
            <p>Cadastre e gerencie os perfis dos estudantes.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>

        <Link to="/professores" className="home-card-link">
          <div className="item-card home-card">
             <div className="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="8.5" cy="7" r="4"></circle><polyline points="17 11 19 13 23 9"></polyline></svg>
            </div>
            <h3>Professores</h3>
            <p>Controle o corpo docente e suas informações.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>

        <Link to="/turmas" className="home-card-link">
          <div className="item-card home-card">
            <div className="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M12 2L2 7l10 5 10-5-10-5z"></path><path d="M2 17l10 5 10-5"></path><path d="M2 12l10 5 10-5"></path></svg>
            </div>
            <h3>Turmas</h3>
            <p>Organize as turmas, matérias e disciplinas.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>
        
        <Link to="/aluno-turma" className="home-card-link">
          <div className="item-card home-card">
            <div className="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><line x1="23" y1="11" x2="17" y2="11"></line><line x1="20" y1="8" x2="20" y2="14"></line></svg>
            </div>
            <h3>Matrículas</h3>
            <p>Associe alunos às suas respectivas turmas.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>
        
        <Link to="/avaliacoes" className="home-card-link">
          <div className="item-card home-card">
            <div className="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="16" y1="13" x2="8" y2="13"></line><line x1="16" y1="17" x2="8" y2="17"></line><polyline points="10 9 9 9 8 9"></polyline></svg>
            </div>
            <h3>Avaliações</h3>
            <p>Crie provas, gerencie notas e histórico.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>
        
        <Link to="/relatorios" className="home-card-link">
          <div className="item-card home-card">
            <div className="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round"><path d="M21.21 15.89A10 10 0 1 1 8 2.83"></path><path d="M22 12A10 10 0 0 0 12 2v10z"></path></svg>
            </div>
            <h3>Relatórios</h3>
            <p>Visualize gráficos de desempenho e estatísticas.</p>
            <span className="card-arrow">&#8594;</span>
          </div>
        </Link>

      </div>
    </div>
  );
};

export default HomePage;
