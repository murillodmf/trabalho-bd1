package murilloGabriel.sistemaAvaliacao.dto;

public class TurmaDTO {
    private Integer cod;
    private String materia;
    private Integer quantidadeAlunos;

    // Informações do Professor
    private Integer registroProfessor;
    private String nomeProfessor;

    // Getters e Setters
    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public Integer getQuantidadeAlunos() { return quantidadeAlunos; }
    public void setQuantidadeAlunos(Integer quantidadeAlunos) { this.quantidadeAlunos = quantidadeAlunos; }
    public Integer getRegistroProfessor() { return registroProfessor; }
    public void setRegistroProfessor(Integer registroProfessor) { this.registroProfessor = registroProfessor; }
    public String getNomeProfessor() { return nomeProfessor; }
    public void setNomeProfessor(String nomeProfessor) { this.nomeProfessor = nomeProfessor; }
}