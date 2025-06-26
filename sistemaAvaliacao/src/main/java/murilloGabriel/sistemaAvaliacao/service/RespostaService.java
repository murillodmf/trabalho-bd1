package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.model.Resposta;
import java.util.List;
import java.util.ArrayList;

public class RespostaService {

    private final List<Resposta> respostas = new ArrayList<>();

    public List<Resposta> listarPorProva(Integer idProva) {
        List<Resposta> resultado = new ArrayList<>();
        for (Resposta r : respostas) {
            if (r.getIdProva().equals(idProva)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public List<Resposta> listarPorAluno(Integer matricula) {
        List<Resposta> resultado = new ArrayList<>();
        for (Resposta r : respostas) {
            if (r.getMatricula().equals(matricula)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public Resposta salvar(Resposta resposta) {
        // Atualiza se existir para mesmo idProva, idQuestao e matricula
        for (Resposta r : respostas) {
            if (r.getIdProva().equals(resposta.getIdProva())
                    && r.getIdQuestao().equals(resposta.getIdQuestao())
                    && r.getMatricula().equals(resposta.getMatricula())) {
                r.setNota(resposta.getNota());
                r.setComentario(resposta.getComentario());
                return r;
            }
        }
        respostas.add(resposta);
        return resposta;
    }

    public boolean deletar(Integer idProva, Integer idQuestao, Integer matricula) {
        return respostas.removeIf(r ->
                r.getIdProva().equals(idProva)
                && r.getIdQuestao().equals(idQuestao)
                && r.getMatricula().equals(matricula)
        );
    }
}
