package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.Professor;

@Repository
public class ProfessorRepository {
    private final JdbcTemplate jdbc;

    public ProfessorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Professor> mapper = (rs, rowNum) -> {
        Professor p = new Professor();
        p.setRegistro(rs.getInt("registro"));
        p.setPnome(rs.getString("p_nome"));
        p.setSnome(rs.getString("s_nome"));
        p.setCpf(rs.getString("cpf"));
        p.setIdade(rs.getInt("idade"));
        return p;
    };

    public void salvar(Professor p) {
        jdbc.update("INSERT INTO professor (registro, p_nome, s_nome, cpf, idade) VALUES (?, ?, ?, ?, ?)",
                p.getRegistro(), p.getPnome(), p.getSnome(), p.getCpf(), p.getIdade());
    }

    public List<Professor> listar() {
        return jdbc.query("SELECT * FROM professor", mapper);
    }

    public Professor buscar(int registro) {
        List<Professor> l = jdbc.query("SELECT * FROM professor WHERE registro = ?", mapper, registro);
        return l.isEmpty() ? null : l.get(0);
    }

    public void atualizar(Professor p) {
        jdbc.update("UPDATE professor SET p_nome = ?, s_nome = ?, cpf = ?, idade = ? WHERE registro = ?",
                p.getPnome(), p.getSnome(), p.getCpf(), p.getIdade(), p.getRegistro());
    }

    public void deletar(int registro) {
        jdbc.update("DELETE FROM professor WHERE registro = ?", registro);
    }
}
