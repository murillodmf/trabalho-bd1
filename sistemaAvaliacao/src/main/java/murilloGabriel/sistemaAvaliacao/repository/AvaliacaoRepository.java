package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AvaliacaoRepository {
    private final JdbcTemplate jdbc;

    public AvaliacaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Avaliacao> mapper = (rs, rowNum) -> {
        Avaliacao a = new Avaliacao();
        a.setIdProva(rs.getInt("idProva"));
        a.setData(rs.getDate("data").toLocalDate());
        a.setNotaMaxima(rs.getDouble("notaMaxima"));
        return a;
    };

    public void salvar(Avaliacao a) {
        jdbc.update("INSERT INTO avaliacao (idProva, data, notaMaxima) VALUES (?, ?, ?)",
                a.getIdProva(), a.getData(), a.getNotaMaxima());
    }

    public List<Avaliacao> listar() {
        return jdbc.query("SELECT * FROM avaliacao", mapper);
    }

    public Avaliacao buscar(int id) {
        List<Avaliacao> l = jdbc.query("SELECT * FROM avaliacao WHERE idProva = ?", mapper, id);
        return l.isEmpty() ? null : l.get(0);
    }

    public void atualizar(Avaliacao a) {
        jdbc.update("UPDATE avaliacao SET data = ?, notaMaxima = ? WHERE idProva = ?",
                a.getData(), a.getNotaMaxima(), a.getIdProva());
    }

    public void deletar(int id) {
        jdbc.update("DELETE FROM avaliacao WHERE idProva = ?", id);
    }
}