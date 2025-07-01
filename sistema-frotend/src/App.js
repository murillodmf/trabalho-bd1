import './App.css';
import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import ProfessoresPage from './pages/ProfessoresPage';
import AlunosPage from './pages/AlunosPage';
import AlunoFormPage from './pages/AlunoFormPage';

function App() {
  return (
      <div>
        <nav>
          <Link to="/">Início</Link>
          <Link to="/alunos">Alunos</Link>
          <Link to="/professores">Professores</Link>
        </nav>

        <hr />

        <main>

          <Routes>
            <Route path="/professores" element={<ProfessoresPage />} />
            <Route path="/alunos" element={<AlunosPage />} />
            <Route path="/alunos/novo" element={<AlunoFormPage />} />
            <Route path="/alunos/editar/:matricula" element={<AlunoFormPage />} />
          </Routes>
        </main>
      </div>
  );
}

export default App;
