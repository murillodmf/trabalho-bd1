package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.*;
import murilloGabriel.sistemaAvaliacao.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin(origins = "http://localhost:3000")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/media-geral-por-turma/{materia}")
    public ResponseEntity<List<MediaTurmaPorMateriaDTO>> getMediaGeralPorTurma(
            @PathVariable String materia) {
        List<MediaTurmaPorMateriaDTO> data = relatorioService.getMediaGeralPorTurma(materia + "%");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/estatisticas-questao/{idQuestao}")
    public ResponseEntity<QuestaoStatsDTO> getEstatisticasPorQuestao(@PathVariable int idQuestao) {
        try {
            QuestaoStatsDTO data = relatorioService.getEstatisticasPorQuestao(idQuestao);
            return ResponseEntity.ok(data);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ranking-alunos/turma/{codTurma}")
    public ResponseEntity<List<RankingAlunoDTO>> getRankingAlunosPorTurma(@PathVariable int codTurma) {
        List<RankingAlunoDTO> data = relatorioService.getRankingAlunosPorTurma(codTurma);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/evolucao-comparativa/aluno/{matriculaAluno}/turma/{codTurma}")
    public ResponseEntity<List<EvolucaoComparativaDTO>> getEvolucaoComparativa(
            @PathVariable int matriculaAluno,
            @PathVariable int codTurma) {
        List<EvolucaoComparativaDTO> data = relatorioService.getEvolucaoComparativa(matriculaAluno, codTurma);
        return ResponseEntity.ok(data);
    }
}