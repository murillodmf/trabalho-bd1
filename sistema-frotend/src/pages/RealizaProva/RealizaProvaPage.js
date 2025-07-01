import React, { useState, useEffect } from 'react';
import { getRealizacoes, deleteRealizacao } from '../../services/RealizaProvaService';
import RealizaProvaForm from './RealizaProvaForm';

const RealizaProvaPage = () => {
  const [realizacoes, setRealizacoes] = useState([]);

  useEffect(() => {
    carregarRealizacoes();
  }, []);

  const carregarRealizacoes = async () => {
    const response = await getRealizacoes();
    setRealizacoes(response.data);
  };

  const handleDelete = async (idProva, idQuestao, matricula) => {
    await deleteRealizacao(idProva, idQuestao, matricula);
    carregarRealizacoes();
  };

  return (
    <div>
      <h1>Provas Realizadas</h1>
      <RealizaProvaForm onSave={carregarRealizacoes} />
      <table>
        <thead>
          <tr>
            <th>Aluno</th>
            <th>Prova</th>
            <th>Questão</th>
            <th>Resposta</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {realizacoes.map((rp) => (
            <tr key={`${rp.idProva}-${rp.idQuestao}-${rp.matricula}`}>
              <td>{rp.aluno.nome}</td>
              <td>{rp.prova.titulo}</td>
              <td>{rp.questao.enunciado.substring(0, 30)}...</td>
              <td>{rp.resposta}</td>
              <td>
                <button onClick={() => handleDelete(rp.idProva, rp.idQuestao, rp.matricula)}>Remover</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default RealizaProvaPage;