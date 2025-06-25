package murilloGabriel.sistemaAvaliacao.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("questao")
public class Questao {
    @Id
    private Integer id;
    private String enunciado;
    private String tipo;
    // Getters e Setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}