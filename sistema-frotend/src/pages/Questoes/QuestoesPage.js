import React, { useState, useEffect } from 'react';
import { getQuestoes, deleteQuestao } from '../../services/QuestaoService';
import QuestaoForm from './QuestaoForm';

const QuestoesPage = () => {
  const [questoes, setQuestoes] = useState([]);
  const [questaoEditando, setQuestaoEditando] = useState(null);

  useEffect(() => {
    carregarQuestoes();
  }, []);

  const carregarQuestoes = async () => {
    const response = await getQuestoes();
    setQuestoes(response.data);
  };

  const handleDelete = async (id) => {
    await deleteQuestao(id);
    carregarQuestoes();
  };

  return (
    <div>
      <h1>Questões</h1>
      <QuestaoForm onSave={carregarQuestoes} questaoEditando={questaoEditando} setQuestaoEditando={setQuestaoEditando} />
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Enunciado</th>
            <th>Tipo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {questoes.map((questao) => (
            <tr key={questao.id}>
              <td>{questao.id}</td>
              <td>{questao.enunciado}</td>
              <td>{questao.tipo}</td>
              <td>
                <button onClick={() => setQuestaoEditando(questao)}>Editar</button>
                <button onClick={() => handleDelete(questao.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default QuestoesPage;