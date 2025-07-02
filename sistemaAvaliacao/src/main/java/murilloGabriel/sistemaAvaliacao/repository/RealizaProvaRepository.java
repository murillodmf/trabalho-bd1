package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.dto.RealizacaoQuestaoDTO;
import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RealizaProvaRepository {
    private final JdbcTemplate jdbc;

    public RealizaProvaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<RealizaProva> realizaProvaMapper = (rs, rowNum) -> {
        RealizaProva r = new RealizaProva();
        r.setIdProva(rs.getInt("id_prova"));
        r.setIdQuestao(rs.getInt("id_questao"));
        r.setMatricula(rs.getInt("matricula"));
        r.setNota(rs.getDouble("nota"));
        r.setComentario(rs.getString("comentario"));
        return r;
    };

    public void salvar(RealizaProva r) {
        jdbc.update("INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES (?, ?, ?, ?, ?)",
                r.getIdProva(), r.getIdQuestao(), r.getMatricula(), r.getNota(), r.getComentario());
    }

    public void atualizar(RealizaProva r) {
        jdbc.update("UPDATE realizaProva SET nota = ?, comentario = ? WHERE id_prova = ? AND id_questao = ? AND matricula = ?",
                r.getNota(), r.getComentario(), r.getIdProva(), r.getIdQuestao(), r.getMatricula());
    }

    public List<RealizaProva> listar() {
        return jdbc.query("SELECT * FROM realizaProva", realizaProvaMapper);
    }

    public Optional<RealizaProva> buscarPorId(int idProva, int idQuestao, int matricula) {
        List<RealizaProva> list = jdbc.query(
                "SELECT * FROM realizaProva WHERE id_prova = ? AND id_questao = ? AND matricula = ?",
                realizaProvaMapper, idProva, idQuestao, matricula
        );
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void deletar(int idProva, int idQuestao, int matricula) {
        jdbc.update("DELETE FROM realizaProva WHERE id_prova = ? AND id_questao = ? AND matricula = ?",
                idProva, idQuestao, matricula);
    }

    public List<RealizacaoQuestaoDTO> listarRealizacoesPorProva(int idProva) {
        String sql = "SELECT rp.id_prova, rp.id_questao, q.enunciado, q.tipo, " +
                "rp.matricula, a.pnome, a.snome, rp.nota, rp.comentario " +
                "FROM realizaProva rp " +
                "JOIN questao q ON rp.id_questao = q.id_questao " +
                "JOIN aluno a ON rp.matricula = a.matricula " +
                "WHERE rp.id_prova = ?";
        return jdbc.query(sql, (rs, rowNum) -> new RealizacaoQuestaoDTO(
                rs.getInt("id_prova"),
                rs.getInt("id_questao"),
                rs.getString("enunciado"),
                rs.getString("tipo"),
                rs.getInt("matricula"),
                rs.getString("pnome") + " " + rs.getString("snome"),
                rs.getDouble("nota"),
                rs.getString("comentario")
        ), idProva);
    }

    public List<Integer> buscarMatriculasAlunosPorTurma(int codTurma) {
        return jdbc.queryForList("SELECT matricula FROM aluno_turma WHERE cod_turma = ?", Integer.class, codTurma);
    }

    public void deletarRealizacoesPorQuestaoDaProva(int idProva, int idQuestao) {
        jdbc.update("DELETE FROM realizaProva WHERE id_prova = ? AND id_questao = ?", idProva, idQuestao);
    }

    public void deletarTodasRealizacoesPorProva(int idProva) {
        jdbc.update("DELETE FROM realizaProva WHERE id_prova = ?", idProva);
    }
}