import logo from './logo.svg';
import './App.css';
import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import ProfessoresPage from './pages/ProfessoresPage';

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
          </Routes>
        </main>
      </div>
  );
}

export default App;
