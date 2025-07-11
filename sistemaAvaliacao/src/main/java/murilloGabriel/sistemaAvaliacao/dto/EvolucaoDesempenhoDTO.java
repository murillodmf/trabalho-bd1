package murilloGabriel.sistemaAvaliacao.dto;

import java.time.LocalDate;

public class EvolucaoDesempenhoDTO {
    private Integer idAvaliacao;
    private String nomeAvaliacao;
    private LocalDate dataAvaliacao;
    private Double notaObtida;
    private Double notaMaximaAvaliacao;

    public EvolucaoDesempenhoDTO(Integer idAvaliacao, String nomeAvaliacao, LocalDate dataAvaliacao, Double notaObtida, Double notaMaximaAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.nomeAvaliacao = nomeAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        this.notaObtida = notaObtida;
        this.notaMaximaAvaliacao = notaMaximaAvaliacao;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getNomeAvaliacao() {
        return nomeAvaliacao;
    }

    public void setNomeAvaliacao(String nomeAvaliacao) {
        this.nomeAvaliacao = nomeAvaliacao;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Double getNotaObtida() {
        return notaObtida;
    }

    public void setNotaObtida(Double notaObtida) {
        this.notaObtida = notaObtida;
    }

    public Double getNotaMaximaAvaliacao() {
        return notaMaximaAvaliacao;
    }

    public void setNotaMaximaAvaliacao(Double notaMaximaAvaliacao) {
        this.notaMaximaAvaliacao = notaMaximaAvaliacao;
    }
}