# 📚 Sistema de Avaliação Escolar

## 📌 Visão Geral

Este projeto tem como objetivo desenvolver um **Sistema de Avaliação para uma Escola do Ensino Médio e Fundamental**. O sistema permite o gerenciamento de avaliações feitas por alunos em diversas disciplinas, com controle de questões, respostas, turmas, professores e resultados.

---

## 🧩 Funcionalidades Principais

### 👤 Gerenciamento de Usuários e Turmas
- Cadastro de alunos com CPF, matrícula, nome e idade, vinculando-os a turmas.
- Criação de turmas com código, disciplina e quantidade de alunos.
- Cadastro de professores com CPF e registro escolar, vinculando-os às turmas.

### 📝 Banco de Questões
- Cadastro de questões **dissertativas** (resposta textual e gabarito) e **objetivas** (com alternativas).
- Criação de um banco de questões reutilizável.

### 📄 Criação e Realização de Avaliações
- Professores montam avaliações selecionando questões do banco.
- As avaliações são armazenadas com identificador único.
- Alunos realizam avaliações de acordo com a turma em que estão matriculados.

### ✅ Correção de Avaliações
- Correção automática de questões objetivas.
- Correção manual de questões dissertativas com base no modelo.
- Professores atribuem notas por questão.

### 📊 Análise de Desempenho
- Análise do desempenho individual do aluno.
- Relacionamento claro entre questões, avaliações, professores e turmas.

### 🛠️ Funcionalidades Administrativas
- Cadastro e vínculo de professores a turmas.
- Criação de turmas e associação de alunos a elas.

---

## 📈 Relatórios Visuais Planejados

- Gráfico de barras: desempenho médio dos alunos por avaliação.
- Gráfico de acertos por questão.
- Gráfico de linha: evolução do desempenho dos alunos ao longo do tempo.
- Gráfico de barras: média de notas por turma ou disciplina.

---

## 🔄 Fluxo do Sistema (Professor)

1. **Cadastro de Questões**: Utiliza a funcionalidade `cadastrarQuestao()`.
2. **Montagem de Provas**: Usa `montarProva()` para selecionar questões e `salvarProva()` para armazená-las.
3. **Correção**: Acessa `corrigirProva()`, corrige objetivas automaticamente, analisa dissertativas e registra notas com `atribuirNota()`.

---

## 💻 Tecnologias Utilizadas

- **Back-end:** Java / J2EE  
- **Front-end:** JavaScript (React)  
- **Banco de Dados:** PostgreSQL  
