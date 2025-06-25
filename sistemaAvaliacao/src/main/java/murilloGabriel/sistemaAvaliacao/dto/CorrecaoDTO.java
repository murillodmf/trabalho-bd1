package murilloGabriel.sistemaAvaliacao.dto;

public class CorrecaoDTO {
    private Double nota;
    private String comentario;

    // Getters e Setters
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}