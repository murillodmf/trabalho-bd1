package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.TurmaDTO;
import murilloGabriel.sistemaAvaliacao.model.Turma;
import murilloGabriel.sistemaAvaliacao.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    /**
     * Endpoint para criar uma nova turma.
     * @param turma O objeto Turma a ser salvo. O 'registroProfessor' deve ser um ID válido.
     * @return O DTO da turma criada com os dados do professor.
     */
    @PostMapping
    public ResponseEntity<TurmaDTO> criarTurma(@RequestBody Turma turma) {
        try {
            Turma turmaSalva = turmaService.salvar(turma);
            TurmaDTO dto = turmaService.buscarPorId(turmaSalva.getCod()).orElse(null);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{cod}")
                    .buildAndExpand(turmaSalva.getCod())
                    .toUri();
            return ResponseEntity.created(location).body(dto);
        } catch (IllegalArgumentException e) {
            // Retorna 400 Bad Request se o ID do professor for inválido
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para listar todas as turmas com os dados dos seus professores.
     * @return Lista de TurmaDTO.
     */
    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }

    /**
     * Endpoint para buscar uma turma pelo seu código.
     * @param cod O código (ID) da turma.
     * @return O TurmaDTO correspondente ou 404 Not Found.
     */
    @GetMapping("/{cod}")
    public ResponseEntity<TurmaDTO> buscarTurmaPorCod(@PathVariable Integer cod) {
        return turmaService.buscarPorId(cod)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para atualizar uma turma.
     * @param cod O código da turma a ser atualizada.
     * @param turma O objeto com os novos dados da turma.
     * @return O TurmaDTO atualizado.
     */
    @PutMapping("/{cod}")
    public ResponseEntity<TurmaDTO> atualizarTurma(@PathVariable Integer cod, @RequestBody Turma turma) {
        return turmaService.buscarPorId(cod)
                .map(turmaExistente -> {
                    turma.setCod(cod); // Garante o ID correto
                    turmaService.salvar(turma);
                    TurmaDTO dtoAtualizado = turmaService.buscarPorId(cod).orElse(null);
                    return ResponseEntity.ok(dtoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para deletar uma turma.
     * @param cod O código da turma a ser deletada.
     * @return Status 204 No Content ou 404 Not Found.
     */
    @DeleteMapping("/{cod}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer cod) {
        if (turmaService.buscarPorId(cod).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        turmaService.deletar(cod);
        return ResponseEntity.noContent().build();
    }
}