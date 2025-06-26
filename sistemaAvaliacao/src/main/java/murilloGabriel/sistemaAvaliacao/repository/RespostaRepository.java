package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Resposta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RespostaRepository {
    private final JdbcTemplate jdbc;

    public RespostaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(Resposta r) {
        jdbc.update("INSERT INTO resposta (idProva, idQuestao, matricula, nota, comentario) VALUES (?, ?, ?, ?, ?)",
                r.getIdProva(), r.getIdQuestao(), r.getMatricula(), r.getNota(), r.getComentario());
    }

    public void deletar(int idProva, int idQuestao, int matricula) {
        jdbc.update("DELETE FROM resposta WHERE idProva = ? AND idQuestao = ? AND matricula = ?",
                idProva, idQuestao, matricula);
    }
}