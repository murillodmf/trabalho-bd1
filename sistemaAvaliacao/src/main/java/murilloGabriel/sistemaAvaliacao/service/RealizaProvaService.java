package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import murilloGabriel.sistemaAvaliacao.repository.RealizaProvaRepository;
import org.springframework.stereotype.Service;

@Service
public class RealizaProvaService {

    private final RealizaProvaRepository repo;

    public RealizaProvaService(RealizaProvaRepository repo) {
        this.repo = repo;
    }

    public void salvar(RealizaProva r) {
        repo.salvar(r);
    }

    public void atualizar(RealizaProva r) {
        repo.atualizar(r);
    }

    public void listar() {
        repo.listar();
    }

    public void deletar(int idProva, int idQuestao, int matricula, Double nota, String comentario) {
        repo.deletar(idProva, idQuestao, matricula, nota, comentario);
    }
}

