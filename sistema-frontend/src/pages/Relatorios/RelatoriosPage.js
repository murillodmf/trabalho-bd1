import React, { useState, useEffect } from 'react';

import { getAlunos } from '../../services/AlunoService';
import { getTurmas } from '../../services/TurmaService';
import { getQuestoes } from '../../services/QuestaoService';
import {
    getMediaGeralPorTurma,
    getAcertosPorQuestao,
    getRankingAlunosPorTurma,     
    getEvolucaoComparativa,      
} from '../../services/RelatorioService';

import MediaPorTurmaChart from '../../components/charts/MediaPorTurmaChart';
import AcertosPorQuestaoChart from '../../components/charts/AcertosPorQuestaoChart';
import RankingAlunosTable from '../../components/charts/RankingAlunosTable';
import EvolucaoComparativaChart from '../../components/charts/EvolucaoComparativaChart'
import './RelatoriosPage.css';

const RelatoriosPage = () => {
    const [mediaGeralData, setMediaGeralData] = useState([]);
    const [acertosQuestaoData, setAcertosQuestaoData] = useState(null);
    const [rankingData, setRankingData] = useState([]);                     
    const [evolucaoComparativaData, setEvolucaoComparativaData] = useState([]);

    
    const [alunos, setAlunos] = useState([]);
    const [turmas, setTurmas] = useState([]);
    const [questoes, setQuestoes] = useState([]);
    const [materiasBase, setMateriasBase] = useState([]);

    const [selectedMateriaMediaGeral, setSelectedMateriaMediaGeral] = useState('');
    const [selectedQuestaoId, setSelectedQuestaoId] = useState('');
    const [selectedTurmaRanking, setSelectedTurmaRanking] = useState('');
    const [selectedAlunoEvolucao, setSelectedAlunoEvolucao] = useState('');
    const [selectedTurmaEvolucao, setSelectedTurmaEvolucao] = useState('');

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadFiltersData = async () => {
            try {
                setLoading(true);
                const [alunosRes, turmasRes, questoesRes] = await Promise.all([
                    getAlunos(), getTurmas(), getQuestoes(),
                ]);
                const turmasData = turmasRes.data || [];
                const materiasUnicas = [...new Set(turmasData.map(t => t.materia.split(' ')[0]))];

                setAlunos(alunosRes.data || []);
                setTurmas(turmasData);
                setQuestoes(questoesRes.data || []);
                setMateriasBase(materiasUnicas);
            } catch (err) {
                setError("Erro ao carregar opções de filtro.");
            } finally {
                setLoading(false);
            }
        };
        loadFiltersData();
    }, []);

    const handleFetchMediaGeral = async () => {
        if (selectedMateriaMediaGeral) {
            const response = await getMediaGeralPorTurma(selectedMateriaMediaGeral);
            setMediaGeralData(response.data);
        }
    };
    
    const handleFetchAcertosQuestao = async () => {
        if (selectedQuestaoId) {
            const response = await getAcertosPorQuestao(selectedQuestaoId);
            setAcertosQuestaoData(response.data);
        }
    };
    
    const handleFetchRanking = async () => {
        if (selectedTurmaRanking) {
            const response = await getRankingAlunosPorTurma(selectedTurmaRanking);
            setRankingData(response.data);
        }
    };

    const handleFetchEvolucaoComparativa = async () => {
        if (selectedAlunoEvolucao && selectedTurmaEvolucao) {
            const response = await getEvolucaoComparativa(selectedAlunoEvolucao, selectedTurmaEvolucao);
            setEvolucaoComparativaData(response.data);
        }
    };

    if (loading) return <p style={{ textAlign: 'center', marginTop: '20px' }}>Carregando dados para relatórios...</p>;
    if (error) return <p className="error-message">{error}</p>;

    return (
        <div className="relatorios-page-container">
            <h1>Relatórios e Gráficos de Avaliação</h1>
            <div className="filters-section section-card">
                <h2>Filtros</h2>

                <div className="filter-block">
                    <h4>Evolução Comparativa (Aluno vs. Turma)</h4>
                    <div className="filter-group">
                        <select value={selectedAlunoEvolucao} onChange={(e) => setSelectedAlunoEvolucao(e.target.value)}>
                            <option value="">Selecione um Aluno</option>
                            {alunos.map(aluno => (<option key={aluno.matricula} value={aluno.matricula}>{aluno.pnome} {aluno.snome} ({aluno.matricula})</option>))}
                        </select>
                        <select value={selectedTurmaEvolucao} onChange={(e) => setSelectedTurmaEvolucao(e.target.value)}>
                            <option value="">Selecione uma Turma</option>
                            {turmas.map(turma => (<option key={turma.cod} value={turma.cod}>{turma.materia}</option>))}
                        </select>
                    </div>
                    <button onClick={handleFetchEvolucaoComparativa} disabled={!selectedAlunoEvolucao || !selectedTurmaEvolucao}>Gerar Gráfico de Evolução</button>
                </div>

                <div className="filter-block">
                    <h4>Média Geral Comparativa das Turmas por Matéria</h4>
                    <div className="filter-group-single">
                        <select value={selectedMateriaMediaGeral} onChange={(e) => setSelectedMateriaMediaGeral(e.target.value)}>
                            <option value="">Selecione uma Matéria</option>
                            {materiasBase.map(materia => (<option key={materia} value={materia}>{materia}</option>))}
                        </select>
                    </div>
                    <button onClick={handleFetchMediaGeral} disabled={!selectedMateriaMediaGeral}>Gerar Gráfico Comparativo</button>
                </div>

                 <div className="filter-block">
                    <h4>Ranking de Alunos por Turma</h4>
                    <div className="filter-group-single">
                        <select value={selectedTurmaRanking} onChange={(e) => setSelectedTurmaRanking(e.target.value)}>
                            <option value="">Selecione uma Turma</option>
                            {turmas.map(turma => (<option key={turma.cod} value={turma.cod}>{turma.materia}</option>))}
                        </select>
                    </div>
                    <button onClick={handleFetchRanking} disabled={!selectedTurmaRanking}>Gerar Ranking</button>
                </div>

                <div className="filter-block">
                    <h4>Percentual de Acertos por Questão</h4>
                    <div className="filter-group-single">
                        <select value={selectedQuestaoId} onChange={(e) => setSelectedQuestaoId(e.target.value)}>
                            <option value="">Selecione uma Questão</option>
                            {questoes.filter(q => q.tipo === 'OBJETIVA').map(q => (<option key={q.idQuestao} value={q.idQuestao}>ID: {q.idQuestao} - {q.enunciado.substring(0, 50)}...</option>))}
                        </select>
                    </div>
                    <button onClick={handleFetchAcertosQuestao} disabled={!selectedQuestaoId}>Gerar Gráfico de Acertos</button>
                </div>
            </div>

            <div className="charts-section">
                <div className="chart-card section-card">
                    <EvolucaoComparativaChart data={evolucaoComparativaData} alunoNome={alunos.find(a=>a.matricula==selectedAlunoEvolucao)?.pnome} turmaNome={turmas.find(t=>t.cod==selectedTurmaEvolucao)?.materia}/>
                </div>
                <div className="chart-card section-card">
                    <MediaPorTurmaChart data={mediaGeralData} titulo={`Média Geral Comparativa em ${selectedMateriaMediaGeral}`} />
                </div>
                <div className="chart-card section-card">
                   <RankingAlunosTable data={rankingData} turmaNome={turmas.find(t=>t.cod==selectedTurmaRanking)?.materia} />
                </div>
                <div className="chart-card section-card">
                    <AcertosPorQuestaoChart data={acertosQuestaoData} />
                </div>
            </div>
        </div>
    );
};

export default RelatoriosPage;