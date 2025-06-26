package murilloGabriel.sistemaAvaliacao.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.Aluno;

@Repository
public class AlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Aluno> alunoMapper = (rs, rowNum) -> {
        Aluno aluno = new Aluno();
        aluno.setMatricula(rs.getInt("matricula"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setPnome(rs.getString("pnome"));
        aluno.setSnome(rs.getString("snome"));
        aluno.setIdade(rs.getInt("idade"));
        return aluno;
    };

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, cpf, pnome, snome, idade) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, aluno.getMatricula(), aluno.getCpf(), aluno.getPnome(), aluno.getSnome(), aluno.getIdade());
    }

    public List<Aluno> buscarTodos() {
        return jdbcTemplate.query("SELECT * FROM aluno", alunoMapper);
    }

    public Aluno buscarPorMatricula(Integer matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        List<Aluno> resultado = jdbcTemplate.query(sql, alunoMapper, matricula);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET cpf = ?, pnome = ?, snome = ?, idade = ? WHERE matricula = ?";
        jdbcTemplate.update(sql, aluno.getCpf(), aluno.getPnome(), aluno.getSnome(), aluno.getIdade(), aluno.getMatricula());
    }

    public void deletar(Integer matricula) {
        jdbcTemplate.update("DELETE FROM aluno WHERE matricula = ?", matricula);
    }
}
