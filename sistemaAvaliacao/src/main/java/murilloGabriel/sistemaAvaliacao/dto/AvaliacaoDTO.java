package murilloGabriel.sistemaAvaliacao.dto;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;

import java.time.LocalDate;
import java.util.List;

public class AvaliacaoDTO {
    private Integer idProva;
    private LocalDate data;
    private Double notaMaxima;
    private Integer codTurma;
    private String nomeTurma;
    private List<Integer> questoesIds;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(Avaliacao avaliacao, String nomeTurma, List<Integer> questoesIds) {
        this.idProva = avaliacao.getIdProva();
        this.data = avaliacao.getData();
        this.notaMaxima = avaliacao.getNotaMaxima();
        this.codTurma = avaliacao.getCodTurma();
        this.nomeTurma = nomeTurma;
        this.questoesIds = questoesIds;
    }

    public Integer getIdProva() {
        return idProva;
    }

    public void setIdProva(Integer idProva) {
        this.idProva = idProva;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getNotaMaxima() {
        return notaMaxima;
    }

    public void setNotaMaxima(Double notaMaxima) {
        this.notaMaxima = notaMaxima;
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

    public List<Integer> getQuestoesIds() {
        return questoesIds;
    }

    public void setQuestoesIds(List<Integer> questoesIds) {
        this.questoesIds = questoesIds;
    }
}