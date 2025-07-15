package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.dto.*;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public AcertosQuestaoDTO getAcertosPorQuestao(int idQuestao) {
        String sqlQuestaoInfo = "SELECT enunciado FROM questao WHERE id_questao = ?";
        String enunciado;
        try {
            enunciado = jdbc.queryForObject(sqlQuestaoInfo, String.class, idQuestao);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Questão com ID " + idQuestao + " não encontrada.", e);
        }

        String sqlContagem = """
            SELECT
                -- Conta o número de alunos únicos que acertaram a questão pelo menos uma vez
                COUNT(DISTINCT CASE WHEN rp.nota > 0 THEN rp.matricula ELSE NULL END) as total_acertos_unicos,
                -- Conta o número total de alunos únicos que responderam a questão
                COUNT(DISTINCT rp.matricula) as total_respostas_unicas
            FROM realizaProva rp
            WHERE rp.id_questao = ?
        """;

        return jdbc.queryForObject(sqlContagem, (rs, rowNum) -> {
            long totalAcertos = rs.getLong("total_acertos_unicos");
            long totalRespostas = rs.getLong("total_respostas_unicas");
            long totalErros = totalRespostas - totalAcertos;

            return new AcertosQuestaoDTO(
                    idQuestao,
                    enunciado,
                    totalRespostas,
                    totalAcertos,
                    totalErros
            );
        }, idQuestao);

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