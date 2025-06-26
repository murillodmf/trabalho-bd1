package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AvaliacaoContemQuestaoRepository {
    private final JdbcTemplate jdbc;

    public AvaliacaoContemQuestaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(AvaliacaoContemQuestao acq) {
        jdbc.update("INSERT INTO avaliacao_contem_questao (idProva, idQuestao) VALUES (?, ?)",
                acq.getIdProva(), acq.getIdQuestao());
    }

    public void deletarPorProva(int idProva) {
        jdbc.update("DELETE FROM avaliacao_contem_questao WHERE idProva = ?", idProva);
    }

    public void deletarPorQuestao(int idQuestao) {
        jdbc.update("DELETE FROM avaliacao_contem_questao WHERE idQuestao = ?", idQuestao);
    }
}