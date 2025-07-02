package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.dto.AvaliacaoDTO;
import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class AvaliacaoRepository {
    private final JdbcTemplate jdbc;

    public AvaliacaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Avaliacao> avaliacaoMapper = (rs, rowNum) -> {
        Avaliacao a = new Avaliacao();
        a.setIdProva(rs.getInt("id_prova"));
        a.setData(rs.getDate("data").toLocalDate());
        a.setNotaMaxima(rs.getDouble("nota_maxima"));
        a.setCodTurma(rs.getInt("cod_turma"));
        return a;
    };

    public Integer salvar(Avaliacao a) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO avaliacao (data, nota_maxima, cod_turma) VALUES (?, ?, ?)",
                    new String[]{"id_prova"}
            );
            ps.setDate(1, java.sql.Date.valueOf(a.getData()));
            ps.setDouble(2, a.getNotaMaxima());
            ps.setInt(3, a.getCodTurma());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : null;
    }

    public List<AvaliacaoDTO> listarComNomeTurma() {
        return jdbc.query(
                "SELECT a.id_prova, a.data, a.nota_maxima, a.cod_turma, t.materia as nome_turma " +
                        "FROM avaliacao a JOIN turma t ON a.cod_turma = t.cod",
                (rs, rowNum) -> {
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setIdProva(rs.getInt("id_prova"));
                    avaliacao.setData(rs.getDate("data").toLocalDate());
                    avaliacao.setNotaMaxima(rs.getDouble("nota_maxima"));
                    avaliacao.setCodTurma(rs.getInt("cod_turma"));
                    return new AvaliacaoDTO(avaliacao, rs.getString("nome_turma"), null);
                }
        );
    }

    public Optional<Avaliacao> buscar(int id) {
        List<Avaliacao> lista = jdbc.query("SELECT * FROM avaliacao WHERE id_prova = ?", avaliacaoMapper, id);
        return lista.isEmpty() ? Optional.empty() : Optional.of(lista.get(0));
    }

    public void atualizar(Avaliacao a) {
        jdbc.update("UPDATE avaliacao SET data = ?, nota_maxima = ?, cod_turma = ? WHERE id_prova = ?",
                a.getData(), a.getNotaMaxima(), a.getCodTurma(), a.getIdProva());
    }

    public void deletar(int id) {
        jdbc.update("DELETE FROM avaliacao WHERE id_prova = ?", id);
    }
}