package murilloGabriel.sistemaAvaliacao.dto;

public class RealizacaoQuestaoDTO {
    private Integer idProva;
    private Integer idQuestao;
    private String enunciadoQuestao;
    private String tipoQuestao;
    private Integer matriculaAluno;
    private String nomeAluno;
    private Double nota;
    private String comentario;

    public RealizacaoQuestaoDTO() {}

    public RealizacaoQuestaoDTO(Integer idProva, Integer idQuestao, String enunciadoQuestao, String tipoQuestao,
                                 Integer matriculaAluno, String nomeAluno, Double nota, String comentario) {
        this.idProva = idProva;
        this.idQuestao = idQuestao;
        this.enunciadoQuestao = enunciadoQuestao;
        this.tipoQuestao = tipoQuestao;
        this.matriculaAluno = matriculaAluno;
        this.nomeAluno = nomeAluno;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Integer getIdProva() { return idProva; }
    public void setIdProva(Integer idProva) { this.idProva = idProva; }
    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public String getEnunciadoQuestao() { return enunciadoQuestao; }
    public void setEnunciadoQuestao(String enunciadoQuestao) { this.enunciadoQuestao = enunciadoQuestao; }
    public String getTipoQuestao() { return tipoQuestao; }
    public void setTipoQuestao(String tipoQuestao) { this.tipoQuestao = tipoQuestao; }
    public Integer getMatriculaAluno() { return matriculaAluno; }
    public void setMatriculaAluno(Integer matriculaAluno) { this.matriculaAluno = matriculaAluno; }
    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}