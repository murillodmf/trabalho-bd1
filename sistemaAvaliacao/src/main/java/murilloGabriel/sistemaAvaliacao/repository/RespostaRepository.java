package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Resposta;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends CrudRepository<Resposta, Object> {

    // Busca todas as respostas de um aluno para uma prova específica.
    @Query("SELECT * FROM resposta WHERE id_prova = :idProva AND matricula_aluno = :matriculaAluno")
    List<Resposta> findByProvaAndAluno(@Param("idProva") Integer idProva, @Param("matriculaAluno") Integer matriculaAluno);

    // Atualiza a nota e o comentário de uma resposta específica (correção).
    @Modifying
    @Query("UPDATE resposta SET nota = :nota, comentario = :comentario WHERE id_prova = :idProva AND id_questao = :idQuestao AND matricula_aluno = :matriculaAluno")
    void corrigirResposta(@Param("idProva") Integer idProva,
                          @Param("idQuestao") Integer idQuestao,
                          @Param("matriculaAluno") Integer matriculaAluno,
                          @Param("nota") Double nota,
                          @Param("comentario") String comentario);
}