import React, { useState, useEffect } from 'react';
import { getAvaliacoes, deleteAvaliacao } from '../../services/AvaliacaoService';
import AvaliacaoForm from './AvaliacaoForm';

const AvaliacoesPage = () => {
  const [avaliacoes, setAvaliacoes] = useState([]);
  const [avaliacaoEditando, setAvaliacaoEditando] = useState(null);

  useEffect(() => {
    carregarAvaliacoes();
  }, []);

  const carregarAvaliacoes = async () => {
    const response = await getAvaliacoes();
    setAvaliacoes(response.data);
  };

  const handleDelete = async (id) => {
    await deleteAvaliacao(id);
    carregarAvaliacoes();
  };

  return (
    <div>
      <h1>Avaliações</h1>
      <AvaliacaoForm onSave={carregarAvaliacoes} avaliacaoEditando={avaliacaoEditando} setAvaliacaoEditando={setAvaliacaoEditando} />
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Data</th>
            <th>Turma</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {avaliacoes.map((avaliacao) => (
            <tr key={avaliacao.id}>
              <td>{avaliacao.id}</td>
              <td>{avaliacao.titulo}</td>
              <td>{new Date(avaliacao.data).toLocaleDateString()}</td>
              <td>{avaliacao.turma?.disciplina}</td>
              <td>
                <button onClick={() => setAvaliacaoEditando(avaliacao)}>Editar</button>
                <button onClick={() => handleDelete(avaliacao.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AvaliacoesPage;