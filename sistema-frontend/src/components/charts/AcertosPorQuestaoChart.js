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
    <div style={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <h3>Acertos por Questão: {data.enunciadoQuestao}</h3>
      <div style={{ width: '100%', height: 300 }}>
        <ResponsiveContainer>
          <PieChart>
            <Pie
              data={chartData}
              cx="50%"
              cy="50%"
              labelLine={false}
              outerRadius={100}
              fill="#8884d8"
              dataKey="value"
              label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
            >
              {chartData.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip formatter={(value) => [`${value} aluno(s)`, 'Quantidade']} />
            <Legend layout="vertical" verticalAlign="middle" align="right" />
          </PieChart>
        </ResponsiveContainer>
      </div>
      <div style={{ marginTop: '20px', textAlign: 'center', lineHeight: '1.5' }}>
        <p><strong>Total de Alunos que Responderam:</strong> {data.totalAlunosQueResponderam}</p>
        <p><strong>Total de Acertos:</strong> {data.totalAcertos}</p>
        <p><strong>Total de Erros:</strong> {data.totalErros}</p>
      </div>
    </div>
  );
};

export default AcertosPorQuestaoChart;