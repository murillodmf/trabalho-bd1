package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import murilloGabriel.sistemaAvaliacao.model.AlunoTurma;
import murilloGabriel.sistemaAvaliacao.model.Turma;

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
    return jdbc.query("SELECT at.*, a.pnome as aluno_pnome, a.snome as aluno_snome, t.disciplina as turma_disciplina " +
            "FROM aluno_turma at " +
            "JOIN aluno a ON at.matricula = a.matricula " +
            "JOIN turma t ON at.cod_turma = t.codigo",
            (rs, rowNum) -> {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setPnome(rs.getString("aluno_pnome"));
                aluno.setSnome(rs.getString("aluno_snome"));
                
                Turma turma = new Turma();
                turma.setCod(rs.getInt("cod_turma"));
                turma.setMateria(rs.getString("turma_disciplina"));
                
                return new AlunoTurma(aluno, turma);
            });
}
}

