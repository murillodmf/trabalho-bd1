package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Professor;
import murilloGabriel.sistemaAvaliacao.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Integer registro) {
        return professorRepository.findById(registro);
    }

    public void deletar(Integer registro) {
        professorRepository.deleteById(registro);
    }
}