package murilloGabriel.sistemaAvaliacao.dto;

public class RankingAlunoDTO {
    private long rank;
    private String nomeAluno;
    private int matricula;
    private Double mediaFinal;

    public RankingAlunoDTO(long rank, String nomeAluno, int matricula, Double mediaFinal) {
        this.rank = rank;
        this.nomeAluno = nomeAluno;
        this.matricula = matricula;
        this.mediaFinal = mediaFinal;
    }

    public long getRank() { return rank; }
    public void setRank(long rank) { this.rank = rank; }

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public Double getMediaFinal() { return mediaFinal; }
    public void setMediaFinal(Double mediaFinal) { this.mediaFinal = mediaFinal; }
}