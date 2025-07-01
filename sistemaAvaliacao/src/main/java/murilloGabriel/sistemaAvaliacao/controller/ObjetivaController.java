package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import murilloGabriel.sistemaAvaliacao.service.ObjetivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objetivas")
public class ObjetivaController {

    @Autowired
    private ObjetivaService service;

    @PostMapping
    public void salvar(@RequestBody Objetiva o) {
        service.salvar(o);
    }

    @PutMapping("/{idQuestao}")
    public void atualizar(@PathVariable int idQuestao, @RequestBody Objetiva o) {
        o.setIdQuestao(idQuestao);
        service.atualizar(o);
    }

    @DeleteMapping("/{idQuestao}")
    public void deletar(@PathVariable int idQuestao) {
        service.deletar(idQuestao);
    }
    
}