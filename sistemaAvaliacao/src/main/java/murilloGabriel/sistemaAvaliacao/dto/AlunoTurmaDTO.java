package murilloGabriel.sistemaAvaliacao.dto;

public class AlunoTurmaDTO {
    private Integer matricula;
    private String nomeAluno;
    private Integer codTurma;
    private String nomeTurma;

    public AlunoTurmaDTO(Integer matricula, String nomeAluno, Integer codTurma, String nomeTurma) {
        this.matricula = matricula;
        this.nomeAluno = nomeAluno;
        this.codTurma = codTurma;
        this.nomeTurma = nomeTurma;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
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

}
