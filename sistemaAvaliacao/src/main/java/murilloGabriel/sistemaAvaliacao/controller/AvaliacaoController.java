package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import murilloGabriel.sistemaAvaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public void salvar(@RequestBody Avaliacao a) {
        service.salvar(a);
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable int id) {
        Avaliacao a = service.buscar(id);
        return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id, @RequestBody Avaliacao a) {
        a.setIdProva(id);
        service.atualizar(a);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        service.deletar(id);
    }
}