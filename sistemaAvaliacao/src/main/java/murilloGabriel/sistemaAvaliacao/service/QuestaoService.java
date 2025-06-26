package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.repository.QuestaoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuestaoService {

    private final QuestaoRepository repo;

    public QuestaoService(QuestaoRepository repo) {
        this.repo = repo;
    }

    public void salvar(Questao q) {
        repo.salvar(q);
    }

    public List<Questao> listar() {
        return repo.listar();
    }

    public Questao buscar(int id) {
        return repo.buscar(id);
    }

    public void atualizar(Questao q) {
        repo.atualizar(q);
    }

    public void deletar(int id) {
        repo.deletar(id);
    }
}
