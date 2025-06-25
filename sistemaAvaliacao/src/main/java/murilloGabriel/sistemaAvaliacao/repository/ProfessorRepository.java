package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Professor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends ListCrudRepository<Professor, Integer> {
    // O Spring Data JDBC cuida das implementações básicas de CRUD (Create, Read, Update, Delete).
    // Não é necessário adicionar métodos aqui para as operações padrão.
}

