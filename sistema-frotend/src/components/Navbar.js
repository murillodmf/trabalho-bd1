import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; // Arquivo de estilos opcional

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
        <Link to="/aluno-turma">Matriculas</Link>
        <Link to="/realiza-prova">Provas Realizadas</Link>
        <Link to="/avaliacao-questoes">Avaliação-Questão</Link>
      </div>
    </nav>
  );
};

export default Navbar;