package murilloGabriel.sistemaAvaliacao.dto;

public class MediaTurmaPorMateriaDTO {
    private Integer codTurma;
    private String nomeTurma;
    private Double mediaNotas;

    public MediaTurmaPorMateriaDTO(Integer codTurma, String nomeTurma, Double mediaNotas) {
        this.codTurma = codTurma;
        this.nomeTurma = nomeTurma;
        this.mediaNotas = mediaNotas;
    }

    public Integer getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(Integer codTurma) {
        this.codTurma = codTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(Double mediaNotas) {
        this.mediaNotas = mediaNotas;
    }
}