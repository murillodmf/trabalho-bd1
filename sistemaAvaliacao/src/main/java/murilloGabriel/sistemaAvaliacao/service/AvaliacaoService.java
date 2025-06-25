package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AvaliacaoService {

    private final JdbcTemplate jdbcTemplate;

    public AvaliacaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Avaliacao> avaliacaoMapper = (rs, rowNum) -> {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdProva(rs.getInt("id_prova"));
        avaliacao.setData(rs.getDate("data").toLocalDate());
        avaliacao.setNotaMaxima(rs.getDouble("nota_maxima"));
        return avaliacao;
    };

    public void salvar(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (id_prova, data, nota_maxima) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                avaliacao.getIdProva(),
                Date.valueOf(avaliacao.getData()),
                avaliacao.getNotaMaxima());
    }

    public List<Avaliacao> buscarTodas() {
        String sql = "SELECT * FROM avaliacao";
        return jdbcTemplate.query(sql, avaliacaoMapper);
    }

    public Avaliacao buscarPorId(Integer id) {
        String sql = "SELECT * FROM avaliacao WHERE id_prova = ?";
        List<Avaliacao> resultado = jdbcTemplate.query(sql, avaliacaoMapper, id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public void atualizar(Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET data = ?, nota_maxima = ? WHERE id_prova = ?";
        jdbcTemplate.update(sql,
                Date.valueOf(avaliacao.getData()),
                avaliacao.getNotaMaxima(),
                avaliacao.getIdProva());
    }

    public void deletar(Integer idProva) {
        String sql = "DELETE FROM avaliacao WHERE id_prova = ?";
        jdbcTemplate.update(sql, idProva);
    }
}
