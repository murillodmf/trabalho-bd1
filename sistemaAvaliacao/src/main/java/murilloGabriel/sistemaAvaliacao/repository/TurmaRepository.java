package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Turma;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends ListCrudRepository<Turma, Integer> {
}