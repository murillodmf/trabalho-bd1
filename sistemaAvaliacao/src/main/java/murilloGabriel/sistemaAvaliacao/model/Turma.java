package murilloGabriel.sistemaAvaliacao.model;

public class Turma {
    
    private Integer cod;
    private String materia;
    private Integer quantidadeAlunos;
    private Integer registroProfessor;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public Integer getRegistroProfessor() {
        return registroProfessor;
    }

    public void setRegistroProfessor(Integer registroProfessor) {
        this.registroProfessor = registroProfessor;
    }
}
