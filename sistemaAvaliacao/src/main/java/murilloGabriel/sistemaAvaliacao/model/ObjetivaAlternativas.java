package murilloGabriel.sistemaAvaliacao.model;

import java.io.Serializable;

public class ObjetivaAlternativas implements Serializable {

    private Integer idQuestao;
    private String alternativa;

    public ObjetivaAlternativas() {
    }

    public ObjetivaAlternativas(Integer idQuestao, String alternativa) {
        this.idQuestao = idQuestao;
        this.alternativa = alternativa;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetivaAlternativas that = (ObjetivaAlternativas) o;
        return java.util.Objects.equals(idQuestao, that.idQuestao) &&
            java.util.Objects.equals(alternativa, that.alternativa);
    }


    @Override
    public int hashCode() {
        return java.util.Objects.hash(idQuestao, alternativa);
    }
}