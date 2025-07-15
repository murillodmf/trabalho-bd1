import React, { useState, useEffect } from 'react';
import { getTurmas } from '../../services/TurmaService';
import { getQuestoes } from '../../services/QuestaoService';
import { createAvaliacao, getAvaliacao, updateAvaliacao } from '../../services/AvaliacaoService';

const AvaliacaoForm = ({ avaliacaoParaEditar, onSave, onCancel }) => {
    const [formData, setFormData] = useState({
        idProva: null,
        data: '',
        notaMaxima: '',
        codTurma: '',
        questoesIds: []
    });
    const [turmas, setTurmas] = useState([]);
    const [questoesDisponiveis, setQuestoesDisponiveis] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadDependencies = async () => {
            try {
                const [turmasRes, questoesRes] = await Promise.all([
                    getTurmas(),
                    getQuestoes()
                ]);
                setTurmas(turmasRes.data);
                setQuestoesDisponiveis(questoesRes.data);
            } catch (err) {
                setError("Erro ao carregar turmas ou questões. Tente novamente.");
                console.error("Erro ao carregar dependências do formulário de avaliação:", err);
            }
        };
        loadDependencies();
    }, []);

    useEffect(() => {
        if (avaliacaoParaEditar) {
            const formattedDate = avaliacaoParaEditar.data ? new Date(avaliacaoParaEditar.data).toISOString().split('T')[0] : '';
            const loadQuestoesAssociadas = async () => {
                try {
                    const res = await getAvaliacao(avaliacaoParaEditar.idProva);
                    setFormData({
                        idProva: res.data.idProva,
                        data: formattedDate,
                        notaMaxima: res.data.notaMaxima || '',
                        codTurma: res.data.codTurma || '',
                        questoesIds: res.data.questoesIds || []
                    });
                } catch (err) {
                    setError("Erro ao carregar questões da avaliação para edição.");
                    console.error("Erro ao carregar questões da avaliação:", err);
                }
            };
            loadQuestoesAssociadas();
        } else {
            resetForm();
        }
    }, [avaliacaoParaEditar]);

    const resetForm = () => {
        setFormData({
            idProva: null,
            data: '',
            notaMaxima: '',
            codTurma: '',
            questoesIds: []
        });
        setError(null);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleQuestaoSelection = (idQuestao) => {
        setFormData(prevData => {
            const isSelected = prevData.questoesIds.includes(idQuestao);
            if (isSelected) {
                return {
                    ...prevData,
                    questoesIds: prevData.questoesIds.filter(id => id !== idQuestao)
                };
            } else {
                return {
                    ...prevData,
                    questoesIds: [...prevData.questoesIds, idQuestao]
                };
            }
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!formData.codTurma || formData.questoesIds.length === 0) {
            setError("Por favor, selecione uma turma e ao menos uma questão.");
            return;
        }

        try {
            if (formData.idProva) {
                await updateAvaliacao(formData.idProva, formData);
            } else {
                await createAvaliacao(formData);
            }
            onSave();
            resetForm();
        } catch (err) {
            setError("Erro ao salvar avaliação. Verifique os dados.");
            console.error("Erro ao salvar avaliação:", err);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>{avaliacaoParaEditar ? 'Editar Avaliação' : 'Criar Nova Avaliação'}</h3>
            {error && <div className="error-message">{error}</div>}

            <div className="form-group">
                <label htmlFor="data">Data da Avaliação:</label>
                <input
                    type="date"
                    id="data"
                    name="data"
                    value={formData.data}
                    onChange={handleInputChange}
                    required
                />
            </div>

            <div className="form-group">
                <label htmlFor="notaMaxima">Nota Máxima:</label>
                <input
                    type="number"
                    id="notaMaxima"
                    name="notaMaxima"
                    value={formData.notaMaxima}
                    onChange={handleInputChange}
                    placeholder="Ex: 100.00"
                    step="0.01"
                    min="0"
                    required
                />
            </div>

            <div className="form-group">
                <label htmlFor="codTurma">Turma:</label>
                <select
                    id="codTurma"
                    name="codTurma"
                    value={formData.codTurma}
                    onChange={handleInputChange}
                    required
                >
                    <option value="">Selecione uma turma</option>
                    {turmas.map(turma => (
                        <option key={turma.cod} value={turma.cod}>
                            {turma.materia} (Código: {turma.cod})
                        </option>
                    ))}
                </select>
            </div>

            <div className="form-group">
                <label>Questões para a Avaliação:</label>
                {questoesDisponiveis.length === 0 ? (
                    <p>Nenhuma questão disponível. Crie questões na página de Questões.</p>
                ) : (
                    <div className="questions-selection-grid">
                        {questoesDisponiveis.map(questao => (
                            <div
                                key={questao.idQuestao}
                                className={`question-item ${formData.questoesIds.includes(questao.idQuestao) ? 'selected' : ''}`}
                                onClick={() => handleQuestaoSelection(questao.idQuestao)}
                            >
                                <input
                                    type="checkbox"
                                    id={`questao-${questao.idQuestao}`}
                                    checked={formData.questoesIds.includes(questao.idQuestao)}
                                    readOnly
                                />
                                <label htmlFor={`questao-${questao.idQuestao}`}>
                                    ID: {questao.idQuestao} - {questao.enunciado.substring(0, 50)}{questao.enunciado.length > 50 ? '...' : ''} ({questao.tipo})
                                </label>
                            </div>
                        ))}
                    </div>
                )}
            </div>

            <div className="form-actions">
                <button type="submit" className="btn btn-primary">
                    {formData.idProva ? 'Atualizar Avaliação' : 'Criar Avaliação'}
                </button>
                {avaliacaoParaEditar && (
                    <button type="button" onClick={onCancel} className="btn btn-secondary">
                        Cancelar
                    </button>
                )}
            </div>
        </form>
    );
};

export default AvaliacaoForm;