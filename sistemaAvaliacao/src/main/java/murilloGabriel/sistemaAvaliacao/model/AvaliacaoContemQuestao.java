package murilloGabriel.sistemaAvaliacao.model;

import java.io.Serializable;
public class AvaliacaoContemQuestao implements Serializable {

    private Integer idProva;
    private Integer idQuestao;

    public AvaliacaoContemQuestao() {
    }

    public AvaliacaoContemQuestao(Integer idProva, Integer idQuestao) {
        this.idProva = idProva;
        this.idQuestao = idQuestao;
    }

    public Integer getIdProva() {
        return idProva;
    }

    public void setIdProva(Integer idProva) {
        this.idProva = idProva;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoContemQuestao that = (AvaliacaoContemQuestao) o;
        return idProva.equals(that.idProva) && idQuestao.equals(that.idQuestao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idProva, idQuestao);
    }
}