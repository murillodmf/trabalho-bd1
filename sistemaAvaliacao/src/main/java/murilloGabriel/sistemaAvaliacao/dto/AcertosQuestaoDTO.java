package murilloGabriel.sistemaAvaliacao.dto;

public class AcertosQuestaoDTO {
    private Integer idQuestao;
    private String enunciadoQuestao;
    private long totalAlunosQueResponderam;
    private long totalAcertos;
    private long totalErros;
    private double percentualAcertos;
    private double percentualErros;

    public AcertosQuestaoDTO(Integer idQuestao, String enunciadoQuestao, long totalAlunosQueResponderam, long totalAcertos, long totalErros) {
        this.idQuestao = idQuestao;
        this.enunciadoQuestao = enunciadoQuestao;
        this.totalAlunosQueResponderam = totalAlunosQueResponderam;
        this.totalAcertos = totalAcertos;
        this.totalErros = totalErros;
        this.percentualAcertos = (totalAlunosQueResponderam > 0) ? ((double) totalAcertos / totalAlunosQueResponderam) * 100 : 0;
        this.percentualErros = (totalAlunosQueResponderam > 0) ? ((double) totalErros / totalAlunosQueResponderam) * 100 : 0;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getEnunciadoQuestao() {
        return enunciadoQuestao;
    }

    public void setEnunciadoQuestao(String enunciadoQuestao) {
        this.enunciadoQuestao = enunciadoQuestao;
    }

    public long getTotalAlunosQueResponderam() {
        return totalAlunosQueResponderam;
    }

    public void setTotalAlunosQueResponderam(long totalAlunosQueResponderam) {
        this.totalAlunosQueResponderam = totalAlunosQueResponderam;
    }

    public long getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(long totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public long getTotalErros() {
        return totalErros;
    }

    public void setTotalErros(long totalErros) {
        this.totalErros = totalErros;
    }

    public double getPercentualAcertos() {
        return percentualAcertos;
    }

    public void setPercentualAcertos(double percentualAcertos) {
        this.percentualAcertos = percentualAcertos;
    }

    public double getPercentualErros() {
        return percentualErros;
    }

    public void setPercentualErros(double percentualErros) {
        this.percentualErros = percentualErros;
    }
}