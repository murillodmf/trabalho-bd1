package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import murilloGabriel.sistemaAvaliacao.service.RealizaProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RealizaProvaController {
    @Autowired
    private RealizaProvaService service;

    @PostMapping
    public void salvar(@RequestBody RealizaProva r) {
        service.salvar(r);
    }

    @PutMapping
    public void atualizar(@RequestBody RealizaProva r) {
        service.atualizar(r);
    }

    @DeleteMapping("/{idProva}/{idQuestao}/{matricula}")
    public void deletar(@PathVariable int idProva, @PathVariable int idQuestao, @PathVariable int matricula) {
        service.deletar(idProva, idQuestao, matricula);
    }
}