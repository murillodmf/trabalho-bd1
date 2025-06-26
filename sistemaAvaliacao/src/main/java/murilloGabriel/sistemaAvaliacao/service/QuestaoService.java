package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import java.util.List;
import java.util.ArrayList;

public class QuestaoService {

    private final List<Questao> questoes = new ArrayList<>();

    public List<Questao> listarTodas() {
        return questoes;
    }

    public Questao buscarPorId(Integer id) {
        return questoes.stream()
                .filter(q -> q.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Questao salvar(Questao questao) {
        Questao existente = buscarPorId(questao.getId());
        if (existente != null) {
            existente.setEnunciado(questao.getEnunciado());
            existente.setTipo(questao.getTipo());
            return existente;
        }
        questoes.add(questao);
        return questao;
    }

    public boolean deletar(Integer id) {
        return questoes.removeIf(q -> q.getId().equals(id));
    }
}
