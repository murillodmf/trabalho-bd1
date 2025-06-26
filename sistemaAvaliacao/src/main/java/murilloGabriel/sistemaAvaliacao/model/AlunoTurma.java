package murilloGabriel.sistemaAvaliacao.model;

import java.io.Serializable;

public class AlunoTurma implements Serializable {

    private Integer matricula;
    private Integer codTurma;

    public AlunoTurma() {
    }

    public AlunoTurma(Integer matricula, Integer codTurma) {
        this.matricula = matricula;
        this.codTurma = codTurma;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(Integer codTurma) {
        this.codTurma = codTurma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoTurma that = (AlunoTurma) o;
        return matricula.equals(that.matricula) && codTurma.equals(that.codTurma);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(matricula, codTurma);
    }
}