package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Resposta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    private final JdbcTemplate jdbcTemplate;

    public RespostaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Resposta> respostaMapper = (rs, rowNum) -> {
        Resposta resposta = new Resposta();
        resposta.setIdProva(rs.getInt("id_prova"));
        resposta.setIdQuestao(rs.getInt("id_questao"));
        resposta.setMatricula(rs.getInt("matricula"));
        resposta.setNota(rs.getDouble("nota"));
        resposta.setComentario(rs.getString("comentario"));
        return resposta;
    };

    public void salvarResposta(Resposta resposta) {
        String sql = "INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                resposta.getIdProva(),
                resposta.getIdQuestao(),
                resposta.getMatricula(),
                resposta.getNota(),
                resposta.getComentario());
    }

    public List<Resposta> buscarPorProva(Integer idProva) {
        String sql = "SELECT * FROM realizaProva WHERE id_prova = ?";
        return jdbcTemplate.query(sql, respostaMapper, idProva);
    }
}
