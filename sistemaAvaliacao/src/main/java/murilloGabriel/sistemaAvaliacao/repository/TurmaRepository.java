package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Turma;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TurmaRepository {
    private final JdbcTemplate jdbc;

    public TurmaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Turma> mapper = (rs, rowNum) -> {
        Turma t = new Turma();
        t.setCod(rs.getInt("cod"));
        t.setMateria(rs.getString("materia"));
        t.setQuantidadeAlunos(rs.getInt("quantidadeAlunos"));
        t.setRegistroProfessor(rs.getInt("registroProfessor"));
        return t;
    };

    public void salvar(Turma t) {
        jdbc.update("INSERT INTO turma (cod, materia, quantidadeAlunos, registroProfessor) VALUES (?, ?, ?, ?)",
                t.getCod(), t.getMateria(), t.getQuantidadeAlunos(), t.getRegistroProfessor());

    }

    public List<Turma> listar() {
        return jdbc.query("SELECT * FROM turma", mapper);
    }

    public void deletar(int cod) {
        jdbc.update("DELETE FROM turma WHERE cod = ?", cod);
    }
}