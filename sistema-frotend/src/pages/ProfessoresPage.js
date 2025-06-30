// Localização: src/pages/ProfessoresPage.js

import React, { useState, useEffect } from 'react';
// Importa a função específica que vamos usar do nosso serviço
import { getAllProfessores } from '../api/professorService';

function ProfessoresPage() {
    // Estados do componente:
    // - 'professores' para guardar a lista que vem da API
    // - 'loading' para sabermos quando mostrar a mensagem "Carregando..."
    // - 'error' para guardar qualquer mensagem de erro que ocorra
    const [professores, setProfessores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // useEffect para buscar os dados assim que o componente for montado
    useEffect(() => {
        const fetchProfessores = async () => {
            try {
                const response = await getAllProfessores(); // Chama a função do nosso serviço
                setProfessores(response.data); // Armazena os dados no estado
            } catch (err) {
                setError('Falha ao buscar professores. Tente novamente mais tarde.'); // Armazena a mensagem de erro
                console.error(err); // Loga o erro detalhado no console do navegador
            } finally {
                setLoading(false); // Para de mostrar a mensagem de "Carregando"
            }
        };

        fetchProfessores();
    }, []); // O array vazio [] garante que a busca só aconteça uma vez

    // Lógica de Renderização
    if (loading) {
        return <p>Carregando professores...</p>;
    }

    if (error) {
        return <p style={{ color: 'red' }}>{error}</p>;
    }

    return (
        <div className="page-container">
            <h1>Gerenciamento de Professores</h1>
            <button>Adicionar Novo Professor</button>

            <table className="data-table">
                <thead>
                <tr>
                    <th>Registro</th>
                    <th>Nome Completo</th> {/* Podemos ajustar o título da coluna */}
                    <th>CPF</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {professores.length > 0 ? (
                    professores.map(professor => (
                        <tr key={professor.registro}>
                            <td>{professor.registro}</td>
                            <td>{`${professor.pnome} ${professor.snome || ''}`}</td>
                            <td>{professor.cpf}</td>
                            <td>
                                <button>Editar</button>
                                <button>Excluir</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="4">Nenhum professor encontrado.</td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}

export default ProfessoresPage;