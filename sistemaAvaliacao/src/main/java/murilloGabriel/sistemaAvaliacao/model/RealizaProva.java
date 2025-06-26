package murilloGabriel.sistemaAvaliacao.model;

import java.io.Serializable;

// ALTERADO: O nome do arquivo e da classe deve ser RealizaProva.java
public class RealizaProva implements Serializable {

    private Integer idProva;
    private Integer idQuestao;
    private Integer matricula;
    private Double nota;
    private String comentario;

    public RealizaProva() {
    }

    public RealizaProva(Integer idProva, Integer idQuestao, Integer matricula, Double nota, String comentario) {
        this.idProva = idProva;
        this.idQuestao = idQuestao;
        this.matricula = matricula;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Integer getIdProva() {
        return idProva;
    }

    public void setIdProva(Integer idProva) {
        this.idProva = idProva;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealizaProva that = (RealizaProva) o;
        return idProva.equals(that.idProva) && idQuestao.equals(that.idQuestao) && matricula.equals(that.matricula);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idProva, idQuestao, matricula);
    }
}