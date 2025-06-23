package murilloGabriel.sistemaAvaliacao.model;

import java.time.LocalDate;

public class Avaliacao {
    
    private Integer id;
    private LocalDate data;
    private Double notaMaxima;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
