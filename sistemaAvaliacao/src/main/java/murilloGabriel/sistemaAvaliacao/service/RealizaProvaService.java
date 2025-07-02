package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.RealizacaoQuestaoDTO;
import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import murilloGabriel.sistemaAvaliacao.repository.RealizaProvaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RealizaProvaService {

    private final RealizaProvaRepository repo;

    public RealizaProvaService(RealizaProvaRepository repo) {
        this.repo = repo;
    }

    public void salvar(RealizaProva r) {
        repo.salvar(r);
    }

    @Transactional
    public void atualizar(RealizaProva r) {
        repo.atualizar(r);
    }

    public List<RealizaProva> listar() {
        return repo.listar();
    }

    public void deletar(int idProva, int idQuestao, int matricula) {
        repo.deletar(idProva, idQuestao, matricula);
    }

    public List<RealizacaoQuestaoDTO> listarRealizacoesPorAvaliacao(int idProva) {
        return repo.listarRealizacoesPorProva(idProva);
    }

    public Optional<RealizacaoQuestaoDTO> buscarRealizacaoDetalhada(int idProva, int idQuestao, int matricula) {
        return repo.listarRealizacoesPorProva(idProva).stream()
                .filter(r -> r.getIdQuestao().equals(idQuestao) && r.getMatriculaAluno().equals(matricula))
                .findFirst();
    }
}