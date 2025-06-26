package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestaoRepository {
    private final JdbcTemplate jdbc;

    public QuestaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Questao> mapper = (rs, rowNum) -> {
        Questao q = new Questao();
        q.setId(rs.getInt("id"));
        q.setEnunciado(rs.getString("enunciado"));
        q.setTipo(rs.getString("tipo"));
        return q;
    };

    public void salvar(Questao q) {
        jdbc.update("INSERT INTO questao (id, enunciado, tipo) VALUES (?, ?, ?)",
                q.getId(), q.getEnunciado(), q.getTipo());
    }

    public List<Questao> listar() {
        return jdbc.query("SELECT * FROM questao", mapper);
    }

    public void deletar(int id) {
        jdbc.update("DELETE FROM questao WHERE id = ?", id);
    }
}