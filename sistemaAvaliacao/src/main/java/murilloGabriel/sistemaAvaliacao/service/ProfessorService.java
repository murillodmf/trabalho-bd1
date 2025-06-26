package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Professor;
import murilloGabriel.sistemaAvaliacao.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProfessorService {

    private final ProfessorRepository repo;

    public ProfessorService(ProfessorRepository repo) {
        this.repo = repo;
    }

    public void salvar(Professor p) {
        repo.salvar(p);
    }

    public List<Professor> listar() {
        return repo.listar();
    }

    public Professor buscar(int registro) {
        return repo.buscar(registro);
    }

    public void atualizar(Professor p) {
        repo.atualizar(p);
    }

    public void deletar(int registro) {
        repo.deletar(registro);
    }
}
