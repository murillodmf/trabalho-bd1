import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; 

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <Link to="/">Sistema de Avaliação</Link>
      </div>
      <div className="navbar-links">
        <Link to="/alunos">Alunos</Link>
        <Link to="/professores">Professores</Link>
        <Link to="/turmas">Turmas</Link>
        <Link to="/questoes">Questões</Link>
        <Link to="/avaliacoes">Avaliações</Link>
        <div className="dropdown">
          <button className="dropbtn">Relacionamentos</button>
          <div className="dropdown-content">
            <Link to="/aluno-turma">Aluno-Turma</Link>
            <Link to="/realiza-prova">Provas Realizadas</Link>
            <Link to="/avaliacao-questoes">Avaliação-Questão</Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;