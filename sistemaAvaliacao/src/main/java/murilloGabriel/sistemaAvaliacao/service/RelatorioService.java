package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.*;
import murilloGabriel.sistemaAvaliacao.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public List<MediaTurmaPorMateriaDTO> getMediaGeralPorTurma(String materia) {
        return relatorioRepository.getMediaGeralPorTurma(materia);
    }

    public AcertosQuestaoDTO getAcertosPorQuestao(int idQuestao) {
        return relatorioRepository.getAcertosPorQuestao(idQuestao);
    }

    public List<RankingAlunoDTO> getRankingAlunosPorTurma(int codTurma) {
        return relatorioRepository.getRankingAlunosPorTurma(codTurma);
    }

    public List<EvolucaoComparativaDTO> getEvolucaoComparativa(int matriculaAluno, int codTurma) {
        return relatorioRepository.getEvolucaoComparativa(matriculaAluno, codTurma);
    }
}