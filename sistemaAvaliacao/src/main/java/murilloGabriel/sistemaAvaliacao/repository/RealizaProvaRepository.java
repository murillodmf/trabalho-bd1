package murilloGabriel.sistemaAvaliacao.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import murilloGabriel.sistemaAvaliacao.model.RealizaProva;

@Repository
public class RealizaProvaRepository {
    private final JdbcTemplate jdbc;

    public RealizaProvaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void salvar(RealizaProva r) {
        jdbc.update("INSERT INTO realizaProva (id_prova, id_questao, matricula, nota, comentario) VALUES (?, ?, ?, ?, ?)",
                r.getIdProva(), r.getIdQuestao(), r.getMatricula(), r.getNota(), r.getComentario());
    }

    public void atualizar(RealizaProva r) {
        jdbc.update("UPDATE realizaProva SET nota = ?, comentario = ? WHERE id_prova = ? AND id_questao = ? AND matricula = ?",
                r.getNota(), r.getComentario(), r.getIdProva(), r.getIdQuestao(), r.getMatricula());
    }

    public void listar() {
        jdbc.query("SELECT * FROM realizaProva", (rs, rowNum) -> {
            RealizaProva r = new RealizaProva();
            r.setIdProva(rs.getInt("id_prova"));
            r.setIdQuestao(rs.getInt("id_questao"));
            r.setMatricula(rs.getInt("matricula"));
            r.setNota(rs.getDouble("nota"));
            r.setComentario(rs.getString("comentario"));
            return r;
        });
        
    }

    public void deletar(int idProva, int idQuestao, int matricula, Double nota, String comentario) {
        jdbc.update("DELETE FROM realizaProva WHERE id_prova = ? AND id_questao = ? AND matricula = ? AND nota = ? AND comentario = ?",
                idProva, idQuestao, matricula);
    }
}
