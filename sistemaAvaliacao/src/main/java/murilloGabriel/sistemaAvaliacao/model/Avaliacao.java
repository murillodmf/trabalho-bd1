package murilloGabriel.sistemaAvaliacao.model;

import java.time.LocalDate;

public class Avaliacao {
    private Integer idProva;
    private LocalDate data;
    private Double notaMaxima;

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
}
