package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.TurmaDTO;
import murilloGabriel.sistemaAvaliacao.model.Turma;
import murilloGabriel.sistemaAvaliacao.repository.ProfessorRepository;
import murilloGabriel.sistemaAvaliacao.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository; // Injetado para buscar dados do professor

    @Transactional
    public Turma salvar(Turma turma) {
        // Valida se o professor referenciado realmente existe
        professorRepository.findById(turma.getRegistroProfessor())
                .orElseThrow(() -> new IllegalArgumentException("Professor com registro " + turma.getRegistroProfessor() + " não encontrado."));

        return turmaRepository.save(turma);
    }

    public void deletar(Integer cod) {
        turmaRepository.deleteById(cod);
    }

    public List<TurmaDTO> listarTodas() {
        return turmaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TurmaDTO> buscarPorId(Integer cod) {
        return turmaRepository.findById(cod).map(this::convertToDto);
    }

    // Método privado para converter uma entidade Turma em um TurmaDTO
    private TurmaDTO convertToDto(Turma turma) {
        TurmaDTO dto = new TurmaDTO();
        dto.setCod(turma.getCod());
        dto.setMateria(turma.getMateria());
        dto.setQuantidadeAlunos(turma.getQuantidadeAlunos());
        dto.setRegistroProfessor(turma.getRegistroProfessor());

        // Busca o nome do professor e adiciona ao DTO
        if (turma.getRegistroProfessor() != null) {
            professorRepository.findById(turma.getRegistroProfessor())
                    .ifPresent(professor -> dto.setNomeProfessor(professor.getNome()));
        }

        return dto;
    }
}