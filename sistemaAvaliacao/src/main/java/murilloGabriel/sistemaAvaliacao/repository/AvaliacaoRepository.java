package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoRepository {
    private final JdbcTemplate jdbc;

    public AvaliacaoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Avaliacao> mapper = (rs, rowNum) -> {
        Avaliacao a = new Avaliacao();
        a.setIdProva(rs.getInt("id_prova"));
        a.setData(rs.getDate("data").toLocalDate());
        a.setNotaMaxima(rs.getDouble("nota_maxima"));
        a.setCodTurma(rs.getInt("cod_turma"));
        return a;
    };

    public void salvar(Avaliacao a) {
        jdbc.update("INSERT INTO avaliacao (id_prova, data, nota_maxima, cod_turma) VALUES (?, ?, ?, ?)",
                a.getIdProva(), a.getData(), a.getNotaMaxima(), a.getCodTurma());
    }

    public List<Avaliacao> listar() {
        return jdbc.query("SELECT * FROM avaliacao", mapper);
    }

    public Avaliacao buscar(int id) {
        List<Avaliacao> lista = jdbc.query("SELECT * FROM avaliacao WHERE id_prova = ?", mapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    public void atualizar(Avaliacao a) {
        jdbc.update("UPDATE avaliacao SET data = ?, nota_maxima = ?, cod_turma = ? WHERE id_prova = ?",
                a.getData(), a.getNotaMaxima(), a.getCodTurma(), a.getIdProva());
    }

    public void deletar(int id) {
        jdbc.update("DELETE FROM avaliacao WHERE id_prova = ?", id);
    }
}
