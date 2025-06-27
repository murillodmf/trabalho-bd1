package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Turma;
import murilloGabriel.sistemaAvaliacao.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @PostMapping
    public void salvar(@RequestBody Turma t) {
        service.salvar(t);
    }

    @GetMapping
    public List<Turma> listar() {
        return service.listar();
    }

    @GetMapping("/{cod}")
    public ResponseEntity<Turma> buscar(@PathVariable int cod) {
        Turma t = service.buscar(cod);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{cod}")
    public void atualizar(@PathVariable int cod, @RequestBody Turma t) {
        t.setCod(cod);
        service.atualizar(t);
    }

    @DeleteMapping("/{cod}")
    public void deletar(@PathVariable int cod) {
        service.deletar(cod);
    }
}