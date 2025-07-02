package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.AvaliacaoDTO;
import murilloGabriel.sistemaAvaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public ResponseEntity<Integer> salvarAvaliacaoCompleta(@RequestBody AvaliacaoDTO dto) {
        Integer idProva = service.salvarAvaliacao(dto);
        return new ResponseEntity<>(idProva, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AvaliacaoDTO> listarAvaliacoes() {
        return service.listarAvaliacoesComDetalhes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> buscarAvaliacao(@PathVariable int id) {
        AvaliacaoDTO dto = service.buscarAvaliacaoComDetalhes(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarAvaliacaoCompleta(@PathVariable int id, @RequestBody AvaliacaoDTO dto) {
        service.atualizarAvaliacao(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable int id) {
        service.deletarAvaliacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}