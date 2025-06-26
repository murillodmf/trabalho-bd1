package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaResposta;
import murilloGabriel.sistemaAvaliacao.repository.ObjetivaRespostaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetivaRespostaService {

    private final ObjetivaRespostaRepository repo;

    public ObjetivaRespostaService(ObjetivaRespostaRepository repo) {
        this.repo = repo;
    }

    public void salvar(ObjetivaResposta or) {
        repo.salvar(or);
    }

    public void deletar(int idQuestao, String alternativa) {
        repo.deletar(idQuestao, alternativa);
    }

    public void deletarTodas(int idQuestao) {
        repo.deletarTodasDeUmaQuestao(idQuestao);
    }
}