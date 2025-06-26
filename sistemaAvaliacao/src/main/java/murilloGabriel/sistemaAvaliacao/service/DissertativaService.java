package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Dissertativa;
import murilloGabriel.sistemaAvaliacao.repository.DissertativaRepository;
import org.springframework.stereotype.Service;

@Service
public class DissertativaService {

    private final DissertativaRepository repo;

    public DissertativaService(DissertativaRepository repo) {
        this.repo = repo;
    }

    public void salvar(Dissertativa d) {
        repo.salvar(d);
    }

    public void atualizar(Dissertativa d) {
        repo.atualizar(d);
    }

    public void deletar(int idQuestao) {
        repo.deletar(idQuestao);
    }
}