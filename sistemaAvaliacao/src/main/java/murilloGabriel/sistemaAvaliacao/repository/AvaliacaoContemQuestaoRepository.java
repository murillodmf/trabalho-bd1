package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoContemQuestaoRepository {
    private final JdbcTemplate jdbc;

    public AvaliacaoContemQuestaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(AvaliacaoContemQuestao acq) {
        jdbc.update("INSERT INTO avaliacao_contem_questao (id_prova, id_questao) VALUES (?, ?)",
                acq.getIdProva(), acq.getIdQuestao());
    }

    public void deletarPorProva(int idProva) {
        jdbc.update("DELETE FROM avaliacao_contem_questao WHERE id_prova = ?", idProva);
    }

    public void deletarPorQuestao(int idQuestao) {
        jdbc.update("DELETE FROM avaliacao_contem_questao WHERE id_questao = ?", idQuestao);
    }

    public void deletar(int idProva, int idQuestao) {
        jdbc.update("DELETE FROM avaliacao_contem_questao WHERE id_prova = ? AND id_questao = ?", idProva, idQuestao);
    }

    public List<Integer> buscarQuestoesIdsPorProva(int idProva) {
        return jdbc.queryForList("SELECT id_questao FROM avaliacao_contem_questao WHERE id_prova = ?", Integer.class, idProva);
    }
}