package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Turma;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
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
        t.setRegistroProfessor(rs.getObject("registro", Integer.class));
        return t;
    };

    public void salvar(Turma t) {
        jdbc.update("INSERT INTO turma (cod, materia, quantidadeAlunos, registro) VALUES (?, ?, ?, ?)",
                t.getCod(), t.getMateria(), t.getQuantidadeAlunos(), t.getRegistroProfessor());
    }

    public List<Turma> listar() {
        return jdbc.query("SELECT * FROM turma", mapper);
    }

    public Turma buscar(int cod) {
        List<Turma> l = jdbc.query("SELECT * FROM turma WHERE cod = ?", mapper, cod);
        return l.isEmpty() ? null : l.get(0);
    }

    public void atualizar(Turma t) {
        jdbc.update("UPDATE turma SET materia = ?, quantidadeAlunos = ?, registro = ? WHERE cod = ?",
                t.getMateria(), t.getQuantidadeAlunos(), t.getRegistroProfessor(), t.getCod());
    }

    public void deletar(int cod) {
        jdbc.update("DELETE FROM turma WHERE cod = ?", cod);
    }
}
