package murilloGabriel.sistemaAvaliacao.model;

import java.io.Serializable;

public class ObjetivaResposta implements Serializable {

    private Integer idQuestao;
    private String alternativa;

    public ObjetivaResposta() {
    }

    public ObjetivaResposta(Integer idQuestao, String alternativa) {
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
        ObjetivaResposta that = (ObjetivaResposta) o;
        return idQuestao.equals(that.idQuestao) && alternativa.equals(that.alternativa);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idQuestao, alternativa);
    }
}