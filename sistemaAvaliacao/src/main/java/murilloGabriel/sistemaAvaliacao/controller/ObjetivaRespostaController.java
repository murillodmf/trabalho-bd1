package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaResposta;
import murilloGabriel.sistemaAvaliacao.service.ObjetivaRespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objetivas-respostas")
public class ObjetivaRespostaController {

    @Autowired
    private ObjetivaRespostaService service;

    @PostMapping
    public void salvar(@RequestBody ObjetivaResposta or) {
        service.salvar(or);
    }

    @DeleteMapping("/{idQuestao}/{alternativa}")
    public void deletar(@PathVariable int idQuestao, @PathVariable String alternativa) {
        service.deletar(idQuestao, alternativa);
    }

    @DeleteMapping("/{idQuestao}")
    public void deletarTodas(@PathVariable int idQuestao) {
        service.deletarTodas(idQuestao);
    }
}