package murilloGabriel.sistemaAvaliacao.model;

public class ObjetivaResposta {

    private Integer idQuestao;
    private String alternativa;

    public ObjetivaResposta() {
    }
    
    public ObjetivaResposta(Integer idQuestao, String alternativa) {
        this.idQuestao = idQuestao;
        this.alternativa = alternativa;
    }

    public ObjetivaResposta(Integer idQuestao) {
        this.idQuestao = idQuestao;
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


}