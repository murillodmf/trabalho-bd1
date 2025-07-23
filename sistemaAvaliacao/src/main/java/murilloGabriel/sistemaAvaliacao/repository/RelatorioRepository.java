package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RelatorioRepository {

    private final JdbcTemplate jdbc;

    public RelatorioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<MediaTurmaPorMateriaDTO> getMediaGeralPorTurma(String materia) {
        String sql = """
            SELECT
                t.cod as cod_turma,
                t.materia as nome_turma,
                (SELECT AVG(s.nota_total)
                 FROM (
                     SELECT SUM(rp.nota) as nota_total
                     FROM realizaProva rp
                     JOIN avaliacao av ON rp.id_prova = av.id_prova
                     WHERE av.cod_turma = t.cod
                     GROUP BY rp.id_prova, rp.matricula
                 ) as s
                ) as media_notas
            FROM turma t
            WHERE t.materia LIKE ?
            GROUP BY t.cod, t.materia
            ORDER BY t.materia;
        """;
        return jdbc.query(sql, (rs, rowNum) -> new MediaTurmaPorMateriaDTO(
                rs.getInt("cod_turma"),
                rs.getString("nome_turma"),
                rs.getDouble("media_notas")
        ), materia);
    }

    public QuestaoStatsDTO getEstatisticasPorQuestao(int idQuestao) {
        String sqlQuestaoInfo = "SELECT enunciado, tipo FROM questao WHERE id_questao = ?";
        
        QuestaoStatsDTO stats = jdbc.queryForObject(sqlQuestaoInfo, (rs, rowNum) -> {
            QuestaoStatsDTO dto = new QuestaoStatsDTO();
            dto.setIdQuestao(idQuestao);
            dto.setEnunciado(rs.getString("enunciado"));
            dto.setTipo(rs.getString("tipo"));
            return dto;
        }, idQuestao);

        if (stats == null) {
            throw new RuntimeException("Questão não encontrada.");
        }

        if ("OBJETIVA".equals(stats.getTipo())) {
            String sqlObjetiva = """
                SELECT
                    COUNT(DISTINCT matricula) as total_respostas,
                    COUNT(DISTINCT CASE WHEN nota > 0 THEN matricula ELSE NULL END) as total_acertos
                FROM realizaProva
                WHERE id_questao = ?
            """;
            jdbc.queryForObject(sqlObjetiva, (rs, rowNum) -> {
                stats.setTotalRespostas(rs.getLong("total_respostas"));
                stats.setTotalAcertos(rs.getLong("total_acertos"));
                stats.setTotalErros(stats.getTotalRespostas() - stats.getTotalAcertos());
                return stats;
            }, idQuestao);
        } 
        else if ("DISSERTATIVA".equals(stats.getTipo())) {
            String sqlDissertativa = """
                SELECT
                    COUNT(*) FILTER (WHERE nota >= 0 AND nota <= 3.99) as faixa1,
                    COUNT(*) FILTER (WHERE nota >= 4 AND nota <= 6.99) as faixa2,
                    COUNT(*) FILTER (WHERE nota >= 7 AND nota <= 10) as faixa3
                FROM realizaProva
                WHERE id_questao = ?
            """;
            jdbc.queryForObject(sqlDissertativa, (rs, rowNum) -> {
                java.util.Map<String, Long> distribuicao = new java.util.LinkedHashMap<>();
                distribuicao.put("Notas 0-3", rs.getLong("faixa1"));
                distribuicao.put("Notas 4-6", rs.getLong("faixa2"));
                distribuicao.put("Notas 7-10", rs.getLong("faixa3"));
                stats.setDistribuicaoNotas(distribuicao);
                return stats;
            }, idQuestao);
        }

        return stats;
    }
    
    public List<RankingAlunoDTO> getRankingAlunosPorTurma(int codTurma) {
        String sql = """
            WITH MediasAlunos AS (
                SELECT
                    a.matricula,
                    a.pnome || ' ' || COALESCE(a.snome, '') as nome_completo,
                    AVG(rp.nota) as media_geral
                FROM realizaProva rp
                JOIN aluno a ON rp.matricula = a.matricula
                WHERE rp.matricula IN (SELECT at.matricula FROM aluno_turma at WHERE at.cod_turma = ?)
                GROUP BY a.matricula, nome_completo
            )
            SELECT
                RANK() OVER (ORDER BY media_geral DESC NULLS LAST) as rank,
                nome_completo,
                matricula,
                media_geral
            FROM MediasAlunos;
        """;
        return jdbc.query(sql, (rs, rowNum) -> new RankingAlunoDTO(
                rs.getLong("rank"),
                rs.getString("nome_completo"),
                rs.getInt("matricula"),
                rs.getDouble("media_geral")
        ), codTurma);
    }

    
    public List<EvolucaoComparativaDTO> getEvolucaoComparativa(int matriculaAluno, int codTurma) {
        String sql = """
            WITH MediasPorProva AS (
                SELECT
                    av.id_prova,
                    AVG(notas_aluno.nota_total) as media_turma
                FROM avaliacao av
                JOIN (
                    SELECT rp.id_prova, rp.matricula, SUM(rp.nota) as nota_total
                    FROM realizaProva rp
                    GROUP BY rp.id_prova, rp.matricula
                ) as notas_aluno ON av.id_prova = notas_aluno.id_prova
                WHERE av.cod_turma = ?
                GROUP BY av.id_prova
            ),
            NotasDoAluno AS (
                SELECT
                    rp.id_prova,
                    SUM(rp.nota) as nota_aluno
                FROM realizaProva rp
                WHERE rp.matricula = ?
                GROUP BY rp.id_prova
            )
            SELECT
                'Avaliação ' || av.id_prova as nome_avaliacao,
                av.data as data_avaliacao,
                na.nota_aluno,
                mp.media_turma
            FROM avaliacao av
            JOIN MediasPorProva mp ON av.id_prova = mp.id_prova
            JOIN NotasDoAluno na ON av.id_prova = na.id_prova
            WHERE av.cod_turma = ?
            ORDER BY av.data;
        """;
        return jdbc.query(sql, (rs, rowNum) -> new EvolucaoComparativaDTO(
                rs.getString("nome_avaliacao"),
                rs.getObject("data_avaliacao", LocalDate.class),
                rs.getDouble("nota_aluno"),
                rs.getDouble("media_turma")
        ), codTurma, matriculaAluno, codTurma);
    }
}