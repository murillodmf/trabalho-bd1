package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import murilloGabriel.sistemaAvaliacao.model.Questao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestaoService {

    private final JdbcTemplate jdbcTemplate;

    public QuestaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvarQuestaoObjetiva(Objetiva objetiva) {
        // Insere na tabela questao
        String sqlQuestao = "INSERT INTO questao (id_questao, enunciado) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuestao, objetiva.getIdQuestao(), "enunciado qualquer");

        // Insere na tabela objetiva
        String sqlObjetiva = "INSERT INTO objetiva (id_questao, resposta) VALUES (?, ?)";
        jdbcTemplate.update(sqlObjetiva, objetiva.getIdQuestao(), objetiva.getRespostaCorreta());

        // Insere as alternativas
        String sqlAlternativas = "INSERT INTO objetiva_resposta (id_questao, alternativa) VALUES (?, ?)";
        for (String alternativa : objetiva.getAlternativas()) {
            jdbcTemplate.update(sqlAlternativas, objetiva.getIdQuestao(), alternativa);
        }
    }

    public Objetiva buscarObjetivaPorId(Integer idQuestao) {
        String sqlResposta = "SELECT resposta FROM objetiva WHERE id_questao = ?";
        String respostaCorreta = jdbcTemplate.queryForObject(sqlResposta, String.class, idQuestao);

        String sqlAlternativas = "SELECT alternativa FROM objetiva_resposta WHERE id_questao = ?";
        List<String> alternativas = jdbcTemplate.query(sqlAlternativas,
                (rs, rowNum) -> rs.getString("alternativa"), idQuestao);

        Objetiva obj = new Objetiva();
        obj.setIdQuestao(idQuestao);
        obj.setRespostaCorreta(respostaCorreta);
        obj.setAlternativas(alternativas);
        return obj;
    }
}
