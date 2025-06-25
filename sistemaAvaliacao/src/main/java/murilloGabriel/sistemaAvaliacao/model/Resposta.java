package murilloGabriel.sistemaAvaliacao.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// Nota: Não usamos @Id aqui porque a chave primária é composta.
// O acesso será feito pelo repositório usando os três campos da chave.
@Table("resposta")
public class Resposta {

    @Column("id_prova")
    private Integer idProva;

    @Column("id_questao")
    private Integer idQuestao;

    @Column("matricula_aluno")
    private Integer matriculaAluno;

    private Double nota;
    private String comentario;

    @Column("resposta_objetiva")
    private String respostaObjetiva;

    @Column("resposta_dissertativa")
    private String respostaDissertativa;

    // Getters e Setters...
    public Integer getIdProva() { return idProva; }
    public void setIdProva(Integer idProva) { this.idProva = idProva; }
    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public Integer getMatriculaAluno() { return matriculaAluno; }
    public void setMatriculaAluno(Integer matriculaAluno) { this.matriculaAluno = matriculaAluno; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public String getRespostaObjetiva() { return respostaObjetiva; }
    public void setRespostaObjetiva(String respostaObjetiva) { this.respostaObjetiva = respostaObjetiva; }
    public String getRespostaDissertativa() { return respostaDissertativa; }
    public void setRespostaDissertativa(String respostaDissertativa) { this.respostaDissertativa = respostaDissertativa; }
}

