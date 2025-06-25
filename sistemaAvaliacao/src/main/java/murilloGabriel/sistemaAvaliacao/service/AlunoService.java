package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final JdbcTemplate jdbcTemplate;

    public AlunoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Aluno> alunoMapper = (rs, rowNum) -> {
        Aluno aluno = new Aluno();
        aluno.setMatricula(rs.getInt("matricula"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setNome(rs.getString("nome"));
        aluno.setIdade(rs.getInt("idade"));
        return aluno;
    };

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, cpf, nome, idade) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                aluno.getMatricula(),
                aluno.getCpf(),
                aluno.getNome(),
                aluno.getIdade());
    }

    public List<Aluno> buscarTodos() {
        String sql = "SELECT * FROM aluno";
        return jdbcTemplate.query(sql, alunoMapper);
    }

    public Aluno buscarPorMatricula(Integer matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        List<Aluno> resultado = jdbcTemplate.query(sql, alunoMapper, matricula);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET cpf = ?, nome = ?, idade = ? WHERE matricula = ?";
        jdbcTemplate.update(sql,
                aluno.getCpf(),
                aluno.getNome(),
                aluno.getIdade(),
                aluno.getMatricula());
    }

    public void deletar(Integer matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";
        jdbcTemplate.update(sql, matricula);
    }
}
