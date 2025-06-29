package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;
import murilloGabriel.sistemaAvaliacao.service.AvaliacaoContemQuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao-questoes")
public class AvaliacaoContemQuestaoController {

    @Autowired
    private AvaliacaoContemQuestaoService service;

    @PostMapping
    public void salvar(@RequestBody AvaliacaoContemQuestao acq) {
        service.salvar(acq);
    }

    @DeleteMapping("/prova/{idProva}")
    public void deletarPorProva(@PathVariable int idProva) {
        service.deletarPorProva(idProva);
    }

    @DeleteMapping("/questao/{idQuestao}")
    public void deletarPorQuestao(@PathVariable int idQuestao) {
        service.deletarPorQuestao(idQuestao);
    }
}