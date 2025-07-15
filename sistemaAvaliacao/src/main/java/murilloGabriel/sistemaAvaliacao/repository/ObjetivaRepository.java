package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ObjetivaRepository {
    private final JdbcTemplate jdbc;

    public ObjetivaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Objetiva> mapper = (rs, rowNum) -> {
        Objetiva o = new Objetiva();
        o.setIdQuestao(rs.getInt("id_questao"));
        o.setRespostaCorreta(rs.getString("respostaCorreta"));
        return o;
    };

    public void salvar(Objetiva o) {
        jdbc.update("INSERT INTO objetiva (id_questao, respostaCorreta) VALUES (?, ?)",
                o.getIdQuestao(), o.getRespostaCorreta());
    }

    public void atualizar(Objetiva o) {
        jdbc.update("UPDATE objetiva SET respostaCorreta = ? WHERE id_questao = ?",
                o.getRespostaCorreta(), o.getIdQuestao());
    }

    public void deletar(int idQuestao) {
        jdbc.update("DELETE FROM objetiva WHERE id_questao = ?", idQuestao);
    }

    public Optional<Objetiva> buscarPorIdQuestao(int idQuestao) {
        List<Objetiva> l = jdbc.query("SELECT * FROM objetiva WHERE id_questao = ?", mapper, idQuestao);
        return l.isEmpty() ? Optional.empty() : Optional.of(l.get(0));
    }
}