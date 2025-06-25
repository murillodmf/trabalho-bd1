package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    // @Autowired
    // private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        // Aluno novoAluno = alunoService.save(aluno);
        // return ResponseEntity.status(201).body(novoAluno);
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        // List<Aluno> alunos = alunoService.findAll();
        // return ResponseEntity.ok(alunos);
        return null;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscarAlunoPorMatricula(@PathVariable Integer matricula) {
        // Aluno aluno = alunoService.findById(matricula);
        // return ResponseEntity.ok(aluno);
        return null;
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Integer matricula, @RequestBody Aluno aluno) {
        // Aluno alunoAtualizado = alunoService.update(matricula, aluno);
        // return ResponseEntity.ok(alunoAtualizado);
        return null;
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer matricula) {
        // alunoService.delete(matricula);
        // return ResponseEntity.noContent().build();
        return null;
    }
}