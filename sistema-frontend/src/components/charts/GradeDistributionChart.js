import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const GradeDistributionChart = ({ data }) => {
  if (!data || !data.distribuicaoNotas) {
    return <p>Sem dados de notas para exibir para esta questão dissertativa.</p>;
  }

  const chartData = Object.entries(data.distribuicaoNotas).map(([name, value]) => ({
    name,
    "Quantidade de Alunos": value,
  }));

  return (
    <div style={{ width: '100%', height: 400 }}>
      <h3>Distribuição de Notas: {data.enunciado}</h3>
      <ResponsiveContainer>
        <BarChart
          data={chartData}
          margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis allowDecimals={false} />
          <Tooltip />
          <Legend />
          <Bar dataKey="Quantidade de Alunos" fill="#8884d8" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default GradeDistributionChart;
