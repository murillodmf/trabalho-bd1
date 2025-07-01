package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.dto.AlunoTurmaDTO;
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

    public List<AlunoTurmaDTO> listar() {
       return jdbc.query(
            "SELECT at.*, a.pnome as aluno_pnome, a.snome as aluno_snome, t.materia as turma_materia " +
            "FROM aluno_turma at " +
            "JOIN aluno a ON at.matricula = a.matricula " +
            "JOIN turma t ON at.cod_turma = t.cod",
            (rs, rowNum) -> new AlunoTurmaDTO(
                rs.getInt("matricula"),
                rs.getString("aluno_pnome") + " " + rs.getString("aluno_snome"),
                rs.getInt("cod_turma"),
                rs.getString("turma_materia")
            )
        );      
    }
}

