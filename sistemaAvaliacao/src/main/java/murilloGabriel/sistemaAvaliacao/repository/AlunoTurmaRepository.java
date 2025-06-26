package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.AlunoTurma;

@Repository
public class AlunoTurmaRepository {
    private final JdbcTemplate jdbc;

    public AlunoTurmaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(AlunoTurma at) {
        jdbc.update("INSERT INTO aluno_turma (matricula, cod_turma) VALUES (?, ?)",
                at.getMatricula(), at.getCodTurma());
    }

    public void deletar(int matricula, int codTurma) {
        jdbc.update("DELETE FROM aluno_turma WHERE matricula = ? AND cod_turma = ?", matricula, codTurma);
    }

    public List<AlunoTurma> listar() {
        return jdbc.query("SELECT * FROM aluno_turma",
                (rs, rowNum) -> new AlunoTurma(rs.getInt("matricula"), rs.getInt("cod_turma")));
    }
}
