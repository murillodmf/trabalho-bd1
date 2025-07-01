package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.ObjetivaAlternativas;
import murilloGabriel.sistemaAvaliacao.service.ObjetivaAlternativasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objetivas/alternativas")
public class ObjetivaAlternativasController {

    @Autowired
    private ObjetivaAlternativasService service;

    @PostMapping
    public void salvar(@RequestBody ObjetivaAlternativas or) {
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