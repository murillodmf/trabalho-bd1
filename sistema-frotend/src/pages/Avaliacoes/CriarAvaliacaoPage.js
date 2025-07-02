import React, { useState, useEffect } from 'react';
import { getAvaliacoes, deleteAvaliacao } from '../../services/AvaliacaoService';
import AvaliacaoForm from './AvaliacaoForm';
import './Avaliacoes.css';

const CriarAvaliacaoPage = () => {
    const [avaliacoes, setAvaliacoes] = useState([]);
    const [avaliacaoEditando, setAvaliacaoEditando] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [successMessage, setSuccessMessage] = useState(null);

    useEffect(() => {
        carregarAvaliacoes();
    }, []);

    const carregarAvaliacoes = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await getAvaliacoes();
            setAvaliacoes(response.data);
        } catch (err) {
            setError("Erro ao carregar avaliações.");
            console.error("Erro ao carregar avaliações:", err);
        } finally {
            setLoading(false);
        }
    };

    const handleSaveSuccess = () => {
        setSuccessMessage("Avaliação salva com sucesso!");
        setAvaliacaoEditando(null);
        carregarAvaliacoes();
        setTimeout(() => setSuccessMessage(null), 3000);
    };

    const handleDelete = async (idProva) => {
        if (window.confirm('Tem certeza que deseja excluir esta avaliação? Todas as realizações de prova relacionadas também serão removidas!')) {
            try {
                await deleteAvaliacao(idProva);
                setSuccessMessage("Avaliação excluída com sucesso!");
                carregarAvaliacoes();
                setTimeout(() => setSuccessMessage(null), 3000);
            } catch (err) {
                setError("Erro ao excluir avaliação.");
                console.error("Erro ao excluir avaliação:", err);
            }
        }
    };

    const handleEditClick = (avaliacao) => {
        setAvaliacaoEditando(avaliacao);
    };

    const handleCancelEdit = () => {
        setAvaliacaoEditando(null);
        setError(null);
    };

    return (
        <div className="page-container">
            <h1>Gerenciamento de Avaliações</h1>
            {error && <div className="error-message">{error}</div>}
            {successMessage && <div className="success-message">{successMessage}</div>}

            <div className="section-card">
                <AvaliacaoForm
                    avaliacaoParaEditar={avaliacaoEditando}
                    onSave={handleSaveSuccess}
                    onCancel={handleCancelEdit}
                />
            </div>

            <div className="section-card">
                <h2>Lista de Avaliações Criadas</h2>
                {loading ? (
                    <p>Carregando avaliações...</p>
                ) : avaliacoes.length === 0 ? (
                    <p>Nenhuma avaliação cadastrada.</p>
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
                                    <button onClick={() => handleEditClick(avaliacao)} className="btn-warning">
                                        Editar
                                    </button>
                                    <button onClick={() => handleDelete(avaliacao.idProva)} className="btn-danger">
                                        Excluir
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default CriarAvaliacaoPage;