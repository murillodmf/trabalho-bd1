package murilloGabriel.sistemaAvaliacao.dto;

import murilloGabriel.sistemaAvaliacao.model.*;

import java.util.List;

public class QuestaoCompletaDTO {
    private Integer idQuestao;
    private String enunciado;
    private String tipo;
    private String respostaCorreta;
    private String respostaModelo;
    private List<String> alternativas;

    public QuestaoCompletaDTO() {}

    public QuestaoCompletaDTO(Questao questao, Objetiva objetiva, List<String> alternativas) {
        this.idQuestao = questao.getIdQuestao();
        this.enunciado = questao.getEnunciado();
        this.tipo = "OBJETIVA";
        this.respostaCorreta = objetiva != null ? objetiva.getRespostaCorreta() : null;
        this.alternativas = alternativas;
    }

    public QuestaoCompletaDTO(Questao questao, Dissertativa dissertativa) {
        this.idQuestao = questao.getIdQuestao();
        this.enunciado = questao.getEnunciado();
        this.tipo = "DISSERTATIVA";
        this.respostaModelo = dissertativa != null ? dissertativa.getRespostaModelo() : null;
    }

    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getRespostaCorreta() { return respostaCorreta; }
    public void setRespostaCorreta(String respostaCorreta) { this.respostaCorreta = respostaCorreta; }
    public String getRespostaModelo() { return respostaModelo; }
    public void setRespostaModelo(String respostaModelo) { this.respostaModelo = respostaModelo; }
    public List<String> getAlternativas() { return alternativas; }
    public void setAlternativas(List<String> alternativas) { this.alternativas = alternativas; }
}
