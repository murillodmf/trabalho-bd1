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

    // 1. Injetar o AlunoService
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        // 2. Chamar o serviço para salvar o aluno
        alunoService.salvar(aluno);
        // 3. Retornar uma resposta com status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        // 4. Chamar o serviço para buscar todos os alunos
        List<Aluno> alunos = alunoService.buscarTodos();
        // 5. Retornar uma resposta com status 200 (OK) e a lista de alunos
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscarAlunoPorMatricula(@PathVariable Integer matricula) {
        // 6. Chamar o serviço para buscar por matrícula
        Aluno aluno = alunoService.buscarPorMatricula(matricula);
        // 7. Verificar se o aluno foi encontrado antes de retornar
        if (aluno != null) {
            return ResponseEntity.ok(aluno); // Status 200 (OK) se encontrado
        } else {
            return ResponseEntity.notFound().build(); // Status 404 (Not Found) se não encontrado
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Integer matricula, @RequestBody Aluno aluno) {
        // 8. O PUT geralmente exige que o ID no corpo corresponda ao ID do path
        // Ou que o ID do path seja usado para buscar e atualizar.
        // Vamos garantir que a matrícula do aluno do corpo seja a mesma da URL, ou atualizá-la
        aluno.setMatricula(matricula); // Garante que a matrícula do objeto seja a da URL
        Aluno alunoExistente = alunoService.buscarPorMatricula(matricula);
        if (alunoExistente != null) {
            alunoService.atualizar(aluno);
            return ResponseEntity.ok(aluno); // Status 200 (OK) após a atualização
        } else {
            return ResponseEntity.notFound().build(); // Status 404 se o aluno não existir para atualizar
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer matricula) {
        // 9. Verificar se o aluno existe antes de tentar deletar
        Aluno alunoExistente = alunoService.buscarPorMatricula(matricula);
        if (alunoExistente != null) {
            alunoService.deletar(matricula);
            return ResponseEntity.noContent().build(); // Status 204 (No Content) após deleção bem-sucedida
        } else {
            return ResponseEntity.notFound().build(); // Status 404 se o aluno não existir
        }
    }
}