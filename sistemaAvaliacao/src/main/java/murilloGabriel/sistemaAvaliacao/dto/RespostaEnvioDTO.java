package murilloGabriel.sistemaAvaliacao.dto;

public class RespostaEnvioDTO {
    private Integer idQuestao;
    private String respostaTexto; // Usado para dissertativas
    private String respostaAlternativa; // Usado para objetivas (ex: "A", "B")

    // Getters e Setters
    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public String getRespostaTexto() { return respostaTexto; }
    public void setRespostaTexto(String respostaTexto) { this.respostaTexto = respostaTexto; }
    public String getRespostaAlternativa() { return respostaAlternativa; }
    public void setRespostaAlternativa(String respostaAlternativa) { this.respostaAlternativa = respostaAlternativa; }
}