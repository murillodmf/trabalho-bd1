package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Resposta;
import murilloGabriel.sistemaAvaliacao.service.RespostaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService = new RespostaService();

    @GetMapping("/prova/{idProva}")
    public List<Resposta> listarPorProva(@PathVariable Integer idProva) {
        return respostaService.listarPorProva(idProva);
    }

    @GetMapping("/aluno/{matricula}")
    public List<Resposta> listarPorAluno(@PathVariable Integer matricula) {
        return respostaService.listarPorAluno(matricula);
    }

    @PostMapping
    public Resposta criar(@RequestBody Resposta resposta) {
        return respostaService.salvar(resposta);
    }

    @PutMapping
    public Resposta atualizar(@RequestBody Resposta resposta) {
        return respostaService.salvar(resposta);
    }

    @DeleteMapping
    public void deletar(
            @RequestParam Integer idProva,
            @RequestParam Integer idQuestao,
            @RequestParam Integer matricula
    ) {
        respostaService.deletar(idProva, idQuestao, matricula);
    }
}
