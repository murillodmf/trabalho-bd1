package murilloGabriel.sistemaAvaliacao.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("dissertativa")
public class Dissertativa {
    @Id
    @Column("id_questao")
    private Integer idQuestao;
    @Column("resposta_modelo")
    private String respostaModelo;
    // Getters e Setters...
    public Integer getIdQuestao() { return idQuestao; }
    public void setIdQuestao(Integer idQuestao) { this.idQuestao = idQuestao; }
    public String getRespostaModelo() { return respostaModelo; }
    public void setRespostaModelo(String respostaModelo) { this.respostaModelo = respostaModelo; }
}