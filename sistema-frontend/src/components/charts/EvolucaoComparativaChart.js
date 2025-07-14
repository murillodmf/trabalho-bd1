import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const EvolucaoComparativaChart = ({ data, alunoNome, turmaNome }) => {
  if (!data || data.length === 0) {
    return <p>Sem dados de evolução comparativa para exibir.</p>;
  }

  const chartData = data.map(item => ({
    name: `${item.nomeAvaliacao} (${new Date(item.dataAvaliacao).toLocaleDateString('pt-BR')})`,
    "Sua Nota": item.notaAluno,
    "Média da Turma": item.mediaTurma ? item.mediaTurma.toFixed(2) : 0,
  }));

  return (
    <div style={{ width: '100%', height: 400 }}>
      <h3>Evolução Comparativa - {alunoNome} vs. Média da Turma {turmaNome}</h3>
      <ResponsiveContainer>
        <LineChart
          data={chartData}
          margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" angle={-15} textAnchor="end" height={60} />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="Sua Nota" stroke="#8884d8" strokeWidth={2} activeDot={{ r: 8 }} />
          <Line type="monotone" dataKey="Média da Turma" stroke="#82ca9d" strokeWidth={2} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default EvolucaoComparativaChart;