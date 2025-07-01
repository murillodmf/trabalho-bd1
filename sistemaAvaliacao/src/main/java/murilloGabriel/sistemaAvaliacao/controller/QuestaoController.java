package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    @Autowired
    private QuestaoService service;

    @PostMapping
    public void salvar(@RequestBody Questao q) {
        service.salvar(q);
    }

    @GetMapping
    public List<Questao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> buscar(@PathVariable int id) {
        Questao q = service.buscar(id);
        return q != null ? ResponseEntity.ok(q) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Questao q) {
        q.setIdQuestao(id);
        service.atualizar(q);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        service.deletar(id);
    }
    
}