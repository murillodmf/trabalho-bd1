package murilloGabriel.sistemaAvaliacao.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("objetiva")
public class Objetiva {
    @Id
    @Column("id_questao")
    private Integer idQuestao;
    @Column("alternativa_a")
    private String alternativaA;
    @Column("alternativa_b")
    private String alternativaB;
    @Column("alternativa_c")
    private String alternativaC;
    @Column("alternativa_d")
    private String alternativaD;
    @Column("alternativa_e")
    private String alternativaE;
    @Column("resposta_correta")
    private String respostaCorreta;
    // Getters e Setters...
    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public String getAlternativaA() { return alternativaA; }
    public void setAlternativaA(String alternativaA) { this.alternativaA = alternativaA; }
    public String getAlternativaB() { return alternativaB; }
    public void setAlternativaB(String alternativaB) { this.alternativaB = alternativaB; }
    public String getAlternativaC() { return alternativaC; }
    public void setAlternativaC(String alternativaC) { this.alternativaC = alternativaC; }
    public String getAlternativaD() { return alternativaD; }
    public void setAlternativaD(String alternativaD) { this.alternativaD = alternativaD; }
    public String getAlternativaE() { return alternativaE; }
    public void setAlternativaE(String alternativaE) { this.alternativaE = alternativaE; }
    public String getRespostaCorreta() { return respostaCorreta; }
    public void setRespostaCorreta(String respostaCorreta) { this.respostaCorreta = respostaCorreta; }
}