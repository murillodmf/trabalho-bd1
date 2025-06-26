package murilloGabriel.sistemaAvaliacao.model;

public class Dissertativa {

    private Integer idQuestao; 
    private String respostaModelo;
    private String resposta;

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getRespostaModelo() {
        return respostaModelo;
    }

    public void setRespostaModelo(String respostaModelo) {
        this.respostaModelo = respostaModelo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}