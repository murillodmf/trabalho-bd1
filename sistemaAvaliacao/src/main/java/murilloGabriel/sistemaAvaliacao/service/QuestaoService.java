package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.repository.QuestaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestaoService {
    private final QuestaoRepository repository;

    public QuestaoService(QuestaoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Questao q) {
        repository.salvar(q);
    }

    public List<Questao> listar() {
        return repository.listar();
    }

    public Questao buscar(int id) {
        return repository.buscar(id);
    }

    public void atualizar(Questao q) {
        repository.atualizar(q);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }
}
