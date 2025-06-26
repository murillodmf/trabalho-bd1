package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import murilloGabriel.sistemaAvaliacao.repository.ObjetivaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetivaService {

    private final ObjetivaRepository repo;

    public ObjetivaService(ObjetivaRepository repo) {
        this.repo = repo;
    }

    public void salvar(Objetiva o) {
        repo.salvar(o);
    }

    public void atualizar(Objetiva o) {
        repo.atualizar(o);
    }

    public void deletar(int idQuestao) {
        repo.deletar(idQuestao);
    }
}