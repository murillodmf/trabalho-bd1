import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';

import HomePage from './pages/Home/HomePage';
import AlunosPage from './pages/Alunos/AlunosPage';
import ProfessoresPage from './pages/Professores/ProfessoresPage';
import TurmasPage from './pages/Turmas/TurmasPage';
import QuestoesPage from './pages/Questoes/QuestoesPage';
import AvaliacoesPage from './pages/Avaliacoes/AvaliacoesPage';
import AlunoTurmaPage from './pages/AlunoTurma/AlunoTurmaPage';
import RealizaProvaPage from './pages/RealizaProva/RealizaProvaPage';
import AvaliacaoContemQuestaoPage from './pages/AvaliacaoContemQuestao/AvaliacaoContemQuestaoPage';

function App() {
  return (
    <Router>
      <div className="app-container">
        <Navbar />
        <div className="content">
          <Routes>
            {/* Rota Home */}
            <Route path="/" element={<HomePage />} />

            {/* Rotas Principais */}
            <Route path="/alunos" element={<AlunosPage />} />
            <Route path="/professores" element={<ProfessoresPage />} />
            <Route path="/turmas" element={<TurmasPage />} />
            <Route path="/questoes" element={<QuestoesPage />} />
            <Route path="/avaliacoes" element={<AvaliacoesPage />} />

            {/* Rotas de Relacionamento */}
            <Route path="/aluno-turma" element={<AlunoTurmaPage />} />
            <Route path="/realiza-prova" element={<RealizaProvaPage />} />
            <Route path="/avaliacao-questoes" element={<AvaliacaoContemQuestaoPage />} />

          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;