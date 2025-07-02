import React from 'react';
import { Link } from 'react-router-dom';
import './Avaliacoes.css';

const AvaliacoesHomePage = () => {
    return (
        <div className="page-container">
            <h1>Página Inicial de Avaliações</h1>
            <p className="description-text">
                Selecione uma opção abaixo para gerenciar as avaliações ou visualizar o histórico de provas realizadas.
            </p>

            <div className="options-grid">
                <Link to="/avaliacoes/criar" className="option-card">
                    <h2>Criar e Gerenciar Avaliações</h2>
                    <p>Crie novas avaliações, adicione questões e edite as existentes.</p>
                    <span className="arrow-icon">&#8594;</span>
                </Link>

                <Link to="/avaliacoes/historico" className="option-card">
                    <h2>Histórico de Provas Realizadas</h2>
                    <p>Visualize as notas dos alunos, faça correções e veja os detalhes das provas.</p>
                    <span className="arrow-icon">&#8594;</span>
                </Link>
            </div>
        </div>
    );
};

export default AvaliacoesHomePage;