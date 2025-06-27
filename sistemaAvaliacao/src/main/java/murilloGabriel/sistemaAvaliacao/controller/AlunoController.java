package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import murilloGabriel.sistemaAvaliacao.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public void salvar(@RequestBody Aluno a) {
        service.salvar(a);
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listar();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscar(@PathVariable int matricula) {
        Aluno a = service.buscar(matricula);
        return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{matricula}")
    public void atualizar(@PathVariable int matricula, @RequestBody Aluno a) {
        a.setMatricula(matricula);
        service.atualizar(a);
    }

    @DeleteMapping("/{matricula}")
    public void deletar(@PathVariable int matricula) {
        service.deletar(matricula);
    }
}