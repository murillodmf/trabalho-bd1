package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Dissertativa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DissertativaRepository {
    private final JdbcTemplate jdbc;

    public DissertativaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Dissertativa> mapper = (rs, rowNum) -> {
        Dissertativa d = new Dissertativa();
        d.setIdQuestao(rs.getInt("id_questao"));
        d.setRespostaModelo(rs.getString("respostaModelo"));
        return d;
    };

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

    public Optional<Dissertativa> buscarPorIdQuestao(int idQuestao) {
        List<Dissertativa> l = jdbc.query("SELECT * FROM dissertativa WHERE id_questao = ?", mapper, idQuestao);
        return l.isEmpty() ? Optional.empty() : Optional.of(l.get(0));
    }
}