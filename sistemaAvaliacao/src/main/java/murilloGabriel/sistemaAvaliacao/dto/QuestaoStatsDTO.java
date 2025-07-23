package murilloGabriel.sistemaAvaliacao.dto;

import java.util.Map;

public class QuestaoStatsDTO {
    private int idQuestao;
    private String enunciado;
    private String tipo;

    private long totalRespostas;
    private long totalAcertos;
    private long totalErros;

    private Map<String, Long> distribuicaoNotas;

    public int getIdQuestao() { return idQuestao; }
    public void setIdQuestao(int idQuestao) { this.idQuestao = idQuestao; }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public long getTotalRespostas() { return totalRespostas; }
    public void setTotalRespostas(long totalRespostas) { this.totalRespostas = totalRespostas; }

    public long getTotalAcertos() { return totalAcertos; }
    public void setTotalAcertos(long totalAcertos) { this.totalAcertos = totalAcertos; }

    public long getTotalErros() { return totalErros; }
    public void setTotalErros(long totalErros) { this.totalErros = totalErros; }

    public Map<String, Long> getDistribuicaoNotas() { return distribuicaoNotas; }
    public void setDistribuicaoNotas(Map<String, Long> distribuicaoNotas) { this.distribuicaoNotas = distribuicaoNotas; }
}
