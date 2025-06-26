package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;

@Service
public class AvaliacaoService {

    private final JdbcTemplate jdbc;

    public AvaliacaoService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(Avaliacao a) {
        String sql = "INSERT INTO avaliacao (idProva, data, notaMaxima) VALUES (?, ?, ?)";
        jdbc.update(sql, a.getIdProva(), a.getData(), a.getNotaMaxima());
    }

    public List<Avaliacao> listar() {
        return jdbc.query("SELECT * FROM avaliacao", (rs, i) -> {
            Avaliacao a = new Avaliacao();
            a.setIdProva(rs.getInt("idProva"));
            a.setData(rs.getDate("data").toLocalDate());
            a.setNotaMaxima(rs.getDouble("notaMaxima"));
            return a;
        });
    }

    public Avaliacao buscarPorId(Integer idProva) {
        List<Avaliacao> resultado = jdbc.query("SELECT * FROM avaliacao WHERE idProva = ?",
                (rs, i) -> {
                    Avaliacao a = new Avaliacao();
                    a.setIdProva(rs.getInt("idProva"));
                    a.setData(rs.getDate("data").toLocalDate());
                    a.setNotaMaxima(rs.getDouble("notaMaxima"));
                    return a;
                }, idProva);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public void atualizar(Avaliacao a) {
        String sql = "UPDATE avaliacao SET data = ?, notaMaxima = ? WHERE idProva = ?";
        jdbc.update(sql, a.getData(), a.getNotaMaxima(), a.getIdProva());
    }

    public void deletar(Integer idProva) {
        String sql = "DELETE FROM avaliacao WHERE idProva = ?";
        jdbc.update(sql, idProva);
    }

}
