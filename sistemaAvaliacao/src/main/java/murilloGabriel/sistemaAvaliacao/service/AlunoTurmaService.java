package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.AlunoTurma;
import murilloGabriel.sistemaAvaliacao.repository.AlunoTurmaRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoTurmaService {

    private final AlunoTurmaRepository repo;

    public AlunoTurmaService(AlunoTurmaRepository repo) {
        this.repo = repo;
    }

    public void salvar(AlunoTurma at) {
        repo.salvar(at);
    }

    public void deletar(int matricula, int codTurma) {
        repo.deletar(matricula, codTurma);
    }

    public List<AlunoTurma> listar() {
        return repo.listar();
    }
}