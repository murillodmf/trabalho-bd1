package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaAlternativas;
import murilloGabriel.sistemaAvaliacao.repository.ObjetivaAlternativasRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetivaAlternativasService {

    private final ObjetivaAlternativasRepository repo;

    public ObjetivaAlternativasService(ObjetivaAlternativasRepository repo) {
        this.repo = repo;
    }

    public void salvar(ObjetivaAlternativas or) {
        repo.salvar(or);
    }

    public void deletar(int idQuestao, String alternativa) {
        repo.deletar(idQuestao, alternativa);
    }

    public void deletarTodas(int idQuestao) {
        repo.deletarTodasDeUmaQuestao(idQuestao);
    }
}