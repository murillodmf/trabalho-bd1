package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;

@Service
public class AvaliacaoContemQuestaoService {

    private final JdbcTemplate jdbc;

    public AvaliacaoContemQuestaoService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void inserir(AvaliacaoContemQuestao acq) {
        String sql = "INSERT INTO avaliacao_contem_questao (idProva, idQuestao) VALUES (?, ?)";
        jdbc.update(sql, acq.getIdProva(), acq.getIdQuestao());
    }

    public List<AvaliacaoContemQuestao> listar() {
        String sql = "SELECT * FROM avaliacao_contem_questao";
        return jdbc.query(sql, (rs, rowNum) -> new AvaliacaoContemQuestao(
                rs.getInt("idProva"),
                rs.getInt("idQuestao")
        ));
    }
}
