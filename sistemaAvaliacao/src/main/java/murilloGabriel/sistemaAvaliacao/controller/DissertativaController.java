package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Dissertativa;
import murilloGabriel.sistemaAvaliacao.service.DissertativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dissertativas")
public class DissertativaController {

    @Autowired
    private DissertativaService service;

    @PostMapping
    public void salvar(@RequestBody Dissertativa d) {
        service.salvar(d);
    }

    @PutMapping("/{idQuestao}")
    public void atualizar(@PathVariable int idQuestao, @RequestBody Dissertativa d) {
        d.setIdQuestao(idQuestao);
        service.atualizar(d);
    }

    @DeleteMapping("/{idQuestao}")
    public void deletar(@PathVariable int idQuestao) {
        service.deletar(idQuestao);
    }
}