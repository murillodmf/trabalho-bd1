package murilloGabriel.sistemaAvaliacao.repository;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository // 1. Anotação CRUCIAL: Diz ao Spring "Eu sou um bean, um componente de repositório!"
public interface AlunoRepository extends ListCrudRepository<Aluno, Integer> {
    // 2. Mágica do Spring Data: Ao estender ListCrudRepository, você
    //    automaticamente ganha os métodos:
    //    - save()       (para criar e atualizar)
    //    - findById()   (para buscar um aluno)
    //    - findAll()    (para listar todos os alunos)
    //    - deleteById() (para deletar)
    //    ...e outros! Não é preciso escrever nenhuma implementação aqui.
}