package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestaoRepository {
    private final JdbcTemplate jdbc;

    public QuestaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Questao> mapper = (rs, rowNum) -> {
        Questao q = new Questao();
        q.setIdQuestao(rs.getInt("id_questao"));
        q.setEnunciado(rs.getString("enunciado"));
        q.setTipo(rs.getString("tipo"));
        return q;
    };

    public void salvar(Questao q) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO questao (enunciado, tipo) VALUES (?, ?)", 
                new String[] { "id_questao" }
            );
            ps.setString(1, q.getEnunciado());
            ps.setString(2, q.getTipo());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            q.setIdQuestao(key.intValue());
        }
    }

    public List<Questao> listar() {
        return jdbc.query("SELECT * FROM questao", mapper);
    }

    public Optional<Questao> buscar(int id) {
        List<Questao> l = jdbc.query("SELECT * FROM questao WHERE id_questao = ?", mapper, id);
        return l.isEmpty() ? Optional.empty() : Optional.of(l.get(0));
    }

    public void atualizar(Questao q) {
        jdbc.update("UPDATE questao SET enunciado = ?, tipo = ? WHERE id_questao = ?",
                q.getEnunciado(), q.getTipo(), q.getIdQuestao());
    }

    public void deletar(int id) {
        jdbc.update("DELETE FROM questao WHERE id_questao = ?", id);
    }
}