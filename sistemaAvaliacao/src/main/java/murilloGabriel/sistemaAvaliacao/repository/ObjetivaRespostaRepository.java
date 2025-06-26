package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaResposta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ObjetivaRespostaRepository {
    private final JdbcTemplate jdbc;

    public ObjetivaRespostaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(ObjetivaResposta or) {
        jdbc.update("INSERT INTO objetiva_resposta (idQuestao, alternativa) VALUES (?, ?)",
                or.getIdQuestao(), or.getAlternativa());
    }

    public void deletar(int idQuestao) {
        jdbc.update("DELETE FROM objetiva_resposta WHERE idQuestao = ?", idQuestao);
    }
}