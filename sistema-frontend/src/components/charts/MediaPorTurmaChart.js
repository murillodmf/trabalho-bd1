import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const MediaPorTurmaChart = ({ data, titulo }) => {
  if (!data || data.length === 0) {
    return <p>Sem dados de média por turma para exibir.</p>;
  }

  const chartData = data.map(item => ({
    name: item.nomeTurma,
    "Média de Notas": item.mediaNotas ? item.mediaNotas.toFixed(2) : 0
  }));

  return (
    <div style={{ width: '100%', height: 400 }}>
      <h3>{titulo}</h3>
      <ResponsiveContainer>
        <BarChart
          data={chartData}
          margin={{ top: 1, right: 30, left: 20, bottom: 45 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          /*<YAxis domain={[0, 'dataMax + 10']} /> 
          <Tooltip />
          <Legend />
          <Bar dataKey="Média de Notas" fill="#8884d8" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default MediaPorTurmaChart;