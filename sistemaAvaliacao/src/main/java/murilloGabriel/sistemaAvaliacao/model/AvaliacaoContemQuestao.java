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

}