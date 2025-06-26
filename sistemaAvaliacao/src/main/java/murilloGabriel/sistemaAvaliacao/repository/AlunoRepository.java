package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.Aluno;

@Repository
public class AlunoRepository {
    private final JdbcTemplate jdbc;

    public AlunoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Aluno> mapper = (rs, rowNum) -> {
        Aluno a = new Aluno();
        a.setMatricula(rs.getInt("matricula"));
        a.setCpf(rs.getString("cpf"));
        a.setPnome(rs.getString("pnome"));
        a.setSnome(rs.getString("snome"));
        a.setIdade(rs.getInt("idade"));
        return a;
    };

    public void salvar(Aluno a) {
        jdbc.update("INSERT INTO aluno (matricula, cpf, pnome, snome, idade) VALUES (?, ?, ?, ?, ?)",
                a.getMatricula(), a.getCpf(), a.getPnome(), a.getSnome(), a.getIdade());
    }

    public List<Aluno> listar() {
        return jdbc.query("SELECT * FROM aluno", mapper);
    }

    public Aluno buscar(int matricula) {
        List<Aluno> l = jdbc.query("SELECT * FROM aluno WHERE matricula = ?", mapper, matricula);
        return l.isEmpty() ? null : l.get(0);
    }

    public void atualizar(Aluno a) {
        jdbc.update("UPDATE aluno SET cpf = ?, pnome = ?, snome = ?, idade = ? WHERE matricula = ?",
                a.getCpf(), a.getPnome(), a.getSnome(), a.getIdade(), a.getMatricula());
    }

    public void deletar(int matricula) {
        jdbc.update("DELETE FROM aluno WHERE matricula = ?", matricula);
    }
}

