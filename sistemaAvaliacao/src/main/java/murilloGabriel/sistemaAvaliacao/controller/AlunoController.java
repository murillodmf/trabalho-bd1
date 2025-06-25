package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Aluno;
import murilloGabriel.sistemaAvaliacao.service.AlunoService; // Importar o AlunoService
import org.springframework.beans.factory.annotation.Autowired; // Necessário para @Autowired
import org.springframework.http.HttpStatus; // Necessário para HttpStatus.CREATED, etc.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.buscarTodos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscarAlunoPorMatricula(@PathVariable Integer matricula) {
        Aluno aluno = alunoService.buscarPorMatricula(matricula);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Integer matricula, @RequestBody Aluno aluno) {
        Aluno alunoExistente = alunoService.buscarPorMatricula(matricula);
        if (alunoExistente != null) {
            alunoService.atualizar(aluno);
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer matricula) {
        Aluno alunoExistente = alunoService.buscarPorMatricula(matricula);
        if (alunoExistente != null) {
            alunoService.deletar(matricula);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}