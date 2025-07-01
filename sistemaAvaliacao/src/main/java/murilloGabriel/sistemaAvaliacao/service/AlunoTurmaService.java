package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.AlunoTurmaDTO;
import murilloGabriel.sistemaAvaliacao.model.AlunoTurma;
import murilloGabriel.sistemaAvaliacao.repository.AlunoTurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    @GetMapping
    public List<AlunoTurmaDTO> listar() {
    return repo.listar();
}

}