package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.QuestaoDTO;
import murilloGabriel.sistemaAvaliacao.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    /**
     * Endpoint para criar uma nova questão (objetiva ou dissertativa).
     * @param questaoDTO O objeto com todos os dados da questão.
     * @return Resposta 201 Created com a questão criada.
     */
    @PostMapping
    public ResponseEntity<QuestaoDTO> criarQuestao(@RequestBody QuestaoDTO questaoDTO) {
        try {
            QuestaoDTO questaoSalva = questaoService.criarQuestao(questaoDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(questaoSalva.getId())
                    .toUri();
            return ResponseEntity.created(location).body(questaoSalva);
        } catch (IllegalArgumentException e) {
            // Retorna um erro 400 Bad Request se o tipo da questão for inválido
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para buscar uma questão completa pelo seu ID.
     * @param id O ID da questão.
     * @return A questão completa (com alternativas ou resposta modelo) ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoDTO> buscarQuestaoPorId(@PathVariable Integer id) {
        return questaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para listar todas as questões.
     * @return Uma lista com todas as questões formatadas em DTO.
     */
    @GetMapping
    public ResponseEntity<List<QuestaoDTO>> listarQuestoes() {
        return ResponseEntity.ok(questaoService.listarTodas());
    }

    /**
     * Endpoint para deletar uma questão.
     * @param id O ID da questão a ser deletada.
     * @return Status 204 No Content se deletado com sucesso, ou 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable Integer id) {
        if (questaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        questaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Nota: A atualização (PUT) seguiria uma lógica parecida com a de criação,
    // mas exigiria primeiro deletar os detalhes antigos (objetiva/dissertativa)
    // antes de criar os novos, para o caso de o tipo da questão mudar.
    // É uma operação mais complexa que pode ser adicionada depois.
}