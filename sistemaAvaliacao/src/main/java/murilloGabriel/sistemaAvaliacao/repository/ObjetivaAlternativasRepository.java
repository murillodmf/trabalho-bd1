package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaAlternativas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ObjetivaAlternativasRepository {
    private final JdbcTemplate jdbc;

    public ObjetivaAlternativasRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(ObjetivaAlternativas or) {
        jdbc.update("INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES (?, ?)",
                or.getIdQuestao(), or.getAlternativa());
    }

    public void deletar(int idQuestao, String alternativa) {
        jdbc.update("DELETE FROM objetiva_resposta WHERE id_questao = ? AND alternativa = ?", idQuestao, alternativa);
    }

    public void deletarTodasDeUmaQuestao(int idQuestao) {
        jdbc.update("DELETE FROM objetiva_resposta WHERE id_questao = ?", idQuestao);
    }
}
