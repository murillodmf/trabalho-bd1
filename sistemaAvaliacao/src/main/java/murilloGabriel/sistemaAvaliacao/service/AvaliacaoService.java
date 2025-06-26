package murilloGabriel.sistemaAvaliacao.service;

import java.util.List;

import murilloGabriel.sistemaAvaliacao.repository.AvaliacaoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repo;

    public AvaliacaoService(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    public void salvar(Avaliacao a) {
        repo.salvar(a);
    }

    public List<Avaliacao> listar() {
        return repo.listar();
    }

    public Avaliacao buscar(int idProva) {
        return repo.buscar(idProva);
    }

    public void atualizar(Avaliacao a) {
        repo.atualizar(a);
    }

    public void deletar(int id) {
        repo.deletar(id);
    }
}