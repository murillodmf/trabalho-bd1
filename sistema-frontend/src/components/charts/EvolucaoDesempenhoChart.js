    import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const EvolucaoDesempenhoChart = ({ data, alunoNome, materiaNome }) => {
  if (!data || data.length === 0) {
    return <p>Sem dados de evolução de desempenho para exibir.</p>;
  }

  const chartData = data.map(item => ({
    name: `Avaliação ${item.idAvaliacao} (${new Date(item.dataAvaliacao).toLocaleDateString('pt-BR')})`,
    "Nota Obtida": item.notaObtida,
    "Nota Máxima": item.notaMaximaAvaliacao
  }));

  return (
    <div style={{ width: '100%', height: 400 }}>
      <h3>Evolução do Desempenho - {alunoNome} em {materiaNome}</h3>
      <ResponsiveContainer>
        <LineChart
          data={chartData}
          margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis domain={[0, 'dataMax']} />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="Nota Obtida" stroke="#8884d8" activeDot={{ r: 8 }} />
          <Line type="monotone" dataKey="Nota Máxima" stroke="#82ca9d" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default EvolucaoDesempenhoChart;