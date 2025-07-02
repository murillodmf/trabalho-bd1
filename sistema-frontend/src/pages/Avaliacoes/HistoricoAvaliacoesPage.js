
import React, { useState, useEffect } from 'react';
import { getAvaliacoes } from '../../services/AvaliacaoService';
import { getRealizacoesPorAvaliacao, updateRealizacao } from '../../services/RealizaProvaService';
import './Avaliacoes.css';
import RealizacaoDetalhesModal from './RealizacaoDetalhesModal';

const HistoricoAvaliacoesPage = () => {
    const [avaliacoes, setAvaliacoes] = useState([]);
    const [loadingAvaliacoes, setLoadingAvaliacoes] = useState(true);
    const [errorAvaliacoes, setErrorAvaliacoes] = useState(null);
    const [selectedAvaliacao, setSelectedAvaliacao] = useState(null);
    const [realizacoesDaAvaliacao, setRealizacoesDaAvaliacao] = useState([]);
    const [loadingRealizacoes, setLoadingRealizacoes] = useState(false);
    const [errorRealizacoes, setErrorRealizacoes] = useState(null);
    const [showRealizacaoModal, setShowRealizacaoModal] = useState(false);
    const [realizacaoEditando, setRealizacaoEditando] = useState(null);

    useEffect(() => {
        carregarAvaliacoes();
    }, []);

    const carregarAvaliacoes = async () => {
        try {
            setLoadingAvaliacoes(true);
            setErrorAvaliacoes(null);
            const response = await getAvaliacoes();
            setAvaliacoes(response.data);
        } catch (err) {
            setErrorAvaliacoes("Erro ao carregar histórico de avaliações.");
            console.error("Erro ao carregar histórico de avaliações:", err);
        } finally {
            setLoadingAvaliacoes(false);
        }
    };

    const handleAvaliacaoClick = async (avaliacao) => {
        setSelectedAvaliacao(avaliacao);
        setLoadingRealizacoes(true);
        setErrorRealizacoes(null);
        try {
            const response = await getRealizacoesPorAvaliacao(avaliacao.idProva);
            setRealizacoesDaAvaliacao(response.data);
        } catch (err) {
            setErrorRealizacoes("Erro ao carregar realizações da avaliação.");
            console.error("Erro ao carregar realizações:", err);
        } finally {
            setLoadingRealizacoes(false);
            setShowRealizacaoModal(true);
        }
    };

    const handleCloseRealizacaoModal = () => {
        setShowRealizacaoModal(false);
        setSelectedAvaliacao(null);
        setRealizacoesDaAvaliacao([]);
        setRealizacaoEditando(null);
    };

    const handleSaveRealizacao = async (realizacaoAtualizada) => {
        try {
            await updateRealizacao(
                realizacaoAtualizada.idProva,
                realizacaoAtualizada.idQuestao,
                realizacaoAtualizada.matriculaAluno,
                {
                    idProva: realizacaoAtualizada.idProva,
                    idQuestao: realizacaoAtualizada.idQuestao,
                    matricula: realizacaoAtualizada.matriculaAluno,
                    nota: realizacaoAtualizada.nota,
                    comentario: realizacaoAtualizada.comentario
                }
            );
            setRealizacoesDaAvaliacao(prev =>
                prev.map(r =>
                    (r.idProva === realizacaoAtualizada.idProva &&
                        r.idQuestao === realizacaoAtualizada.idQuestao &&
                        r.matriculaAluno === realizacaoAtualizada.matriculaAluno)
                        ? realizacaoAtualizada
                        : r
                )
            );
            setRealizacaoEditando(null);
            alert("Realização atualizada com sucesso!");
        } catch (err) {
            alert("Erro ao salvar a correção. Tente novamente.");
            console.error("Erro ao salvar realização:", err);
        }
    };

    return (
        <div className="page-container">
            <h1>Histórico de Avaliações</h1>
            {errorAvaliacoes && <div className="error-message">{errorAvaliacoes}</div>}

            <div className="section-card">
                <h2>Avaliações Realizadas</h2>
                {loadingAvaliacoes ? (
                    <p>Carregando avaliações...</p>
                ) : avaliacoes.length === 0 ? (
                    <p>Nenhuma avaliação disponível no histórico.</p>
                ) : (
                    <table className="data-table">
                        <thead>
                        <tr>
                            <th>ID Prova</th>
                            <th>Data</th>
                            <th>Nota Máxima</th>
                            <th>Turma</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {avaliacoes.map((avaliacao) => (
                            <tr key={avaliacao.idProva}>
                                <td>{avaliacao.idProva}</td>
                                <td>{new Date(avaliacao.data).toLocaleDateString('pt-BR')}</td>
                                <td>{avaliacao.notaMaxima.toFixed(2)}</td>
                                <td>{avaliacao.nomeTurma || 'N/A'}</td>
                                <td className="actions-column">
                                    <button onClick={() => handleAvaliacaoClick(avaliacao)} className="btn-primary">
                                        Ver Alunos e Notas
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>

            {showRealizacaoModal && selectedAvaliacao && (
                <RealizacaoDetalhesModal
                    avaliacao={selectedAvaliacao}
                    realizacoes={realizacoesDaAvaliacao}
                    loading={loadingRealizacoes}
                    error={errorRealizacoes}
                    onClose={handleCloseRealizacaoModal}
                    onSaveRealizacao={handleSaveRealizacao}
                />
            )}
        </div>
    );
};

export default HistoricoAvaliacoesPage;