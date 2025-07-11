package murilloGabriel.sistemaAvaliacao.dto;

import java.time.LocalDate;

public class EvolucaoComparativaDTO {
    private String nomeAvaliacao;
    private LocalDate dataAvaliacao;
    private Double notaAluno;
    private Double mediaTurma;

    public EvolucaoComparativaDTO(String nomeAvaliacao, LocalDate dataAvaliacao, Double notaAluno, Double mediaTurma) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        this.notaAluno = notaAluno;
        this.mediaTurma = mediaTurma;
    }

    public String getNomeAvaliacao() { return nomeAvaliacao; }
    public void setNomeAvaliacao(String nomeAvaliacao) { this.nomeAvaliacao = nomeAvaliacao; }

    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    public Double getNotaAluno() { return notaAluno; }
    public void setNotaAluno(Double notaAluno) { this.notaAluno = notaAluno; }

    public Double getMediaTurma() { return mediaTurma; }
    public void setMediaTurma(Double mediaTurma) { this.mediaTurma = mediaTurma; }
}