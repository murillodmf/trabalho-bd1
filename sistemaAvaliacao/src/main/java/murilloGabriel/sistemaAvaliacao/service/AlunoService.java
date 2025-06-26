package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import murilloGabriel.sistemaAvaliacao.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void salvar(Aluno aluno) {
        alunoRepository.salvar(aluno);
    }

    public List<Aluno> buscarTodos() {
        return alunoRepository.buscarTodos();
    }

    public Aluno buscarPorMatricula(Integer matricula) {
        return alunoRepository.buscarPorMatricula(matricula);
    }

    public void atualizar(Aluno aluno) {
        alunoRepository.atualizar(aluno);
    }

    public void deletar(Integer matricula) {
        alunoRepository.deletar(matricula);
    }
}
