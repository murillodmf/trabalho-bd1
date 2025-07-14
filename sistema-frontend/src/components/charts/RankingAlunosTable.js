import React from 'react';
import './RankingAlunosTable.css';

const RankingAlunosTable = ({ data, turmaNome }) => {
    if (!data || data.length === 0) {
        return <p>Sem dados de ranking para exibir. Selecione uma turma e gere o relatório.</p>;
    }

    return (
        <div className="ranking-container">
            <h3>Ranking de Alunos da Turma: {turmaNome}</h3>
            <table className="ranking-table">
                <thead>
                <tr>
                    <th>Posição</th>
                    <th>Aluno</th>
                    <th>Matrícula</th>
                    <th>Média Geral</th>
                </tr>
                </thead>
                <tbody>
                {data.map((aluno) => (
                    <tr key={aluno.matricula}>
                        <td className="rank-cell">{aluno.rank}º</td>
                        <td>{aluno.nomeAluno}</td>
                        <td>{aluno.matricula}</td>
                        <td>{aluno.mediaFinal ? aluno.mediaFinal.toFixed(2) : 'N/A'}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default RankingAlunosTable;