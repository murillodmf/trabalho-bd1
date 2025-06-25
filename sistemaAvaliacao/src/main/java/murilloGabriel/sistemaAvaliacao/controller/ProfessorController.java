package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Professor;
import murilloGabriel.sistemaAvaliacao.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    /**
     * Endpoint para cadastrar um novo professor.
     * @param professor Dados do professor a serem salvos.
     * @return Resposta com status 201 Created e o professor salvo no corpo.
     */
    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor professorSalvo = professorService.salvar(professor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{registro}")
                .buildAndExpand(professorSalvo.getRegistro())
                .toUri();
        return ResponseEntity.created(location).body(professorSalvo);
    }

    /**
     * Endpoint para listar todos os professores.
     * @return Lista de todos os professores com status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    /**
     * Endpoint para buscar um professor pelo seu número de registro.
     * @param registro Chave primária do professor.
     * @return O professor encontrado (200 OK) ou 404 Not Found.
     */
    @GetMapping("/{registro}")
    public ResponseEntity<Professor> buscarProfessorPorRegistro(@PathVariable Integer registro) {
        return professorService.buscarPorId(registro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para atualizar os dados de um professor.
     * @param registro Chave primária do professor a ser atualizado.
     * @param professor Objeto com os novos dados.
     * @return O professor com os dados atualizados (200 OK) ou 404 Not Found.
     */
    @PutMapping("/{registro}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Integer registro, @RequestBody Professor professor) {
        return professorService.buscarPorId(registro)
                .map(professorExistente -> {
                    professor.setRegistro(registro); // Assegura que o ID está correto
                    Professor professorAtualizado = professorService.salvar(professor);
                    return ResponseEntity.ok(professorAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para deletar um professor.
     * @param registro Chave primária do professor a ser deletado.
     * @return Status 204 No Content em caso de sucesso ou 404 Not Found.
     */
    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Integer registro) {
        if (professorService.buscarPorId(registro).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        professorService.deletar(registro);
        return ResponseEntity.noContent().build();
    }
}