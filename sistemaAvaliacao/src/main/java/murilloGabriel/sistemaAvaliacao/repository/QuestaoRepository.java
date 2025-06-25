// QuestaoRepository.java
package murilloGabriel.sistemaAvaliacao.repository;
import murilloGabriel.sistemaAvaliacao.model.Questao;
import org.springframework.data.repository.ListCrudRepository;
@org.springframework.stereotype.Repository
public interface QuestaoRepository extends ListCrudRepository<Questao, Integer> {}

