package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Dissertativa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DissertativaRepository {
    private final JdbcTemplate jdbc;

    public DissertativaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(Dissertativa d) {
        jdbc.update("INSERT INTO dissertativa (id_questao, respostaModelo) VALUES (?, ?)",
            d.getIdQuestao(), d.getRespostaModelo());
    }   

    public void atualizar(Dissertativa d) {
        jdbc.update("UPDATE dissertativa SET respostaModelo = ? WHERE id_questao = ?",
            d.getRespostaModelo(), d.getIdQuestao());
    }

    public void deletar(int idQuestao) {
        jdbc.update("DELETE FROM dissertativa WHERE id_questao = ?", idQuestao);
    }
}