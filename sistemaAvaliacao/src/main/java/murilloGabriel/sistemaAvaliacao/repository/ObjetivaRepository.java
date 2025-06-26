package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ObjetivaRepository {
    private final JdbcTemplate jdbc;

    public ObjetivaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(Objetiva o) {
        jdbc.update("INSERT INTO objetiva (idQuestao, respostaCorreta) VALUES (?, ?)",
                o.getIdQuestao(), o.getRespostaCorreta());
        for (String alt : o.getAlternativas()) {
            jdbc.update("INSERT INTO alternativa (idQuestao, texto) VALUES (?, ?)", o.getIdQuestao(), alt);
        }
    }

    public void deletar(int idQuestao) {
        jdbc.update("DELETE FROM alternativa WHERE idQuestao = ?", idQuestao);
        jdbc.update("DELETE FROM objetiva WHERE idQuestao = ?", idQuestao);
    }
}