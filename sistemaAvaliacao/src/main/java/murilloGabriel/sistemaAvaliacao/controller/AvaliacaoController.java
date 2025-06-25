package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import murilloGabriel.sistemaAvaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    /**
     * Endpoint para criar uma nova avaliação.
     * @param avaliacao O objeto Avaliacao vindo no corpo da requisição.
     * @return ResponseEntity com a avaliação criada e status 201 Created.
     */
    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoSalva = avaliacaoService.salvar(avaliacao);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(avaliacaoSalva.getId())
                .toUri();
        return ResponseEntity.created(location).body(avaliacaoSalva);
    }

    /**
     * Endpoint para listar todas as avaliações.
     * @return ResponseEntity com a lista de avaliações.
     */
    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.listarTodas();
        return ResponseEntity.ok(avaliacoes);
    }

    /**
     * Endpoint para buscar uma avaliação pelo seu ID.
     * @param id O ID da avaliação.
     * @return ResponseEntity com a avaliação encontrada ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Integer id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para atualizar uma avaliação existente.
     * @param id O ID da avaliação a ser atualizada.
     * @param avaliacao O objeto Avaliacao com os dados atualizados.
     * @return ResponseEntity com a avaliação atualizada ou 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Integer id, @RequestBody Avaliacao avaliacao) {
        return avaliacaoService.buscarPorId(id)
                .map(avaliacaoExistente -> {
                    avaliacao.setId(id); // Garante que o ID correto está sendo usado.
                    Avaliacao avaliacaoAtualizada = avaliacaoService.salvar(avaliacao);
                    return ResponseEntity.ok(avaliacaoAtualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para deletar uma avaliação.
     * @param id O ID da avaliação a ser deletada.
     * @return ResponseEntity com status 204 No Content ou 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Integer id) {
        if (avaliacaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}