import React from 'react';
import { PieChart, Pie, Cell, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const COLORS = ['#00C49F', '#FF8042'];

const AcertosPorQuestaoChart = ({ data }) => {
  if (!data || data.totalAlunosQueResponderam === 0) {
    return <p>Sem dados de acertos por questão para exibir.</p>;
  }

  const chartData = [
    { name: 'Acertos', value: data.totalAcertos },
    { name: 'Erros', value: data.totalErros },
  ];

  return (
    <div style={{ width: '100%', height: 400, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <h3>Acertos por Questão: {data.enunciadoQuestao}</h3>
      <ResponsiveContainer width="100%" height={300}>
        <PieChart>
          <Pie
            data={chartData}
            cx="50%"
            cy="50%"
            labelLine={false}
            outerRadius={100}
            fill="#8884d8"
            dataKey="value"
          >
            {chartData.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip formatter={(value, name) => [`${value} (${(value / data.totalAlunosQueResponderam * 100).toFixed(2)}%)`, name]} />
          <Legend />
        </PieChart>
      </ResponsiveContainer>
      <div style={{ marginTop: '20px', textAlign: 'center' }}>
        <p><strong>Total de Alunos que Responderam:</strong> {data.totalAlunosQueResponderam}</p>
        <p><strong>Total de Acertos:</strong> {data.totalAcertos}</p>
        <p><strong>Total de Erros:</strong> {data.totalErros}</p>
      </div>
    </div>
  );
};

export default AcertosPorQuestaoChart;