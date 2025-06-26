package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Turma;
import murilloGabriel.sistemaAvaliacao.repository.TurmaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    private final TurmaRepository repo;

    public TurmaService(TurmaRepository repo) {
        this.repo = repo;
    }

    public void salvar(Turma turma) {
        repo.salvar(turma);
    }

    public List<Turma> listar() {
        return repo.listar();
    }

    public Turma buscar(int cod) {
        return repo.buscar(cod);
    }

    public void atualizar(Turma turma) {
        repo.atualizar(turma);
    }

    public void deletar(int cod) {
        repo.deletar(cod);
    }
}
