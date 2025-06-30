package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Professor;
import murilloGabriel.sistemaAvaliacao.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @PostMapping
    public void salvar(@RequestBody Professor p) {
        service.salvar(p);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listar() {
        List<Professor> professores = service.listar();
        return ResponseEntity.ok(professores); // A mudança para garantir um JSON válido
    }

    @GetMapping("/{registro}")
    public ResponseEntity<Professor> buscar(@PathVariable int registro) {
        Professor p = service.buscar(registro);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{registro}")
    public void atualizar(@PathVariable int registro, @RequestBody Professor p) {
        p.setRegistro(registro);
        service.atualizar(p);
    }

    @DeleteMapping("/{registro}")
    public void deletar(@PathVariable int registro) {
        service.deletar(registro);
    }
}