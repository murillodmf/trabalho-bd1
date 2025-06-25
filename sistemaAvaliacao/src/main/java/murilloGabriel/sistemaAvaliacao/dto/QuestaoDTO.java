package murilloGabriel.sistemaAvaliacao.dto;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.model.Dissertativa;

import java.util.List;

// Um DTO para carregar todas as informações de uma questão em um único objeto.
public class QuestaoDTO {

    // Dados da Questão Base
    private Integer id;
    private String enunciado;
    private String tipo; // "objetiva" ou "dissertativa"

    // Dados específicos da Questão Objetiva
    private List<String> alternativas;
    private String respostaCorreta; // "A", "B", "C", etc.

    // Dados específicos da Questão Dissertativa
    private String respostaModelo;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public List<String> getAlternativas() { return alternativas; }
    public void setAlternativas(List<String> alternativas) { this.alternativas = alternativas; }
    public String getRespostaCorreta() { return respostaCorreta; }
    public void setRespostaCorreta(String respostaCorreta) { this.respostaCorreta = respostaCorreta; }
    public String getRespostaModelo() { return respostaModelo; }
    public void setRespostaModelo(String respostaModelo) { this.respostaModelo = respostaModelo; }
}