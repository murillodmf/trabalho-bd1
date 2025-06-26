package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import murilloGabriel.sistemaAvaliacao.repository.AvaliacaoContemQuestaoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;

@Service
public class AvaliacaoContemQuestaoService {

    private final AvaliacaoContemQuestaoRepository repo;

    public AvaliacaoContemQuestaoService(AvaliacaoContemQuestaoRepository repo) {
        this.repo = repo;
    }

    public void salvar(AvaliacaoContemQuestao acq) {
        repo.salvar(acq);
    }

    public void deletarPorProva(int idProva) {
        repo.deletarPorProva(idProva);
    }

    public void deletarPorQuestao(int idQuestao) {
        repo.deletarPorQuestao(idQuestao);
    }
}