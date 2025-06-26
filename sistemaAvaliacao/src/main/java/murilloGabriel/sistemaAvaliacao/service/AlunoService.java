package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import murilloGabriel.sistemaAvaliacao.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo) {
        this.repo = repo;
    }

    public void salvar(Aluno aluno) {
        repo.salvar(aluno);
    }

    public List<Aluno> listar() {
        return repo.listar();
    }

    public Aluno buscar(int matricula) {
        return repo.buscar(matricula);
    }

    public void atualizar(Aluno aluno) {
        repo.atualizar(aluno);
    }

    public void deletar(int matricula) {
        repo.deletar(matricula);
    }
}
