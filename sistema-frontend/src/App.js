import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';

import HomePage from './pages/Home/HomePage';
import AlunosPage from './pages/Alunos/AlunosPage';
import ProfessoresPage from './pages/Professores/ProfessoresPage';
import TurmasPage from './pages/Turmas/TurmasPage';
import QuestoesPage from './pages/Questoes/QuestoesPage';

import AvaliacoesHomePage from './pages/Avaliacoes/AvaliacoesHomePage';
import CriarAvaliacaoPage from './pages/Avaliacoes/CriarAvaliacaoPage';
import HistoricoAvaliacoesPage from './pages/Avaliacoes/HistoricoAvaliacoesPage';

import AlunoTurmaPage from './pages/AlunoTurma/AlunoTurmaPage';
import RelatoriosPage from './pages/Relatorios/RelatoriosPage';

import './global.css';

function App() {
  return (
    <Router>
      <div className="app-container">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/alunos" element={<AlunosPage />} />
            <Route path="/professores" element={<ProfessoresPage />} />
            <Route path="/turmas" element={<TurmasPage />} />
            <Route path="/questoes" element={<QuestoesPage />} />
            <Route path="/avaliacoes" element={<AvaliacoesHomePage />} />
            <Route path="/avaliacoes/criar" element={<CriarAvaliacaoPage />} />
            <Route path="/avaliacoes/historico" element={<HistoricoAvaliacoesPage />} />
            <Route path="/aluno-turma" element={<AlunoTurmaPage />} />
            <Route path="/relatorios" element={<RelatoriosPage />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;