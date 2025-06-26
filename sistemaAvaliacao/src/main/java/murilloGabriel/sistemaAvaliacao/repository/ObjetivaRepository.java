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
        jdbc.update("INSERT INTO objetiva (id_questao, resposta) VALUES (?, ?)",
                o.getIdQuestao(), o.getRespostaCorreta());
    }

    public void atualizar(Objetiva o) {
        jdbc.update("UPDATE objetiva SET resposta = ? WHERE id_questao = ?",
                o.getRespostaCorreta(), o.getIdQuestao());
    }

    public void deletar(int idQuestao) {
        jdbc.update("DELETE FROM objetiva WHERE id_questao = ?", idQuestao);
    }
}
