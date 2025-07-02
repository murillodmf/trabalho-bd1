package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.RealizacaoQuestaoDTO;
import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import murilloGabriel.sistemaAvaliacao.service.RealizaProvaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realizaProva")
public class RealizaProvaController {

    @Autowired
    private RealizaProvaService service;

    @PostMapping
    public ResponseEntity<Void> salvarRealizacao(@RequestBody RealizaProva r) {
        service.salvar(r);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idProva}/{idQuestao}/{matricula}")
    public ResponseEntity<Void> atualizarRealizacao(
            @PathVariable int idProva,
            @PathVariable int idQuestao,
            @PathVariable int matricula,
            @RequestBody RealizaProva r) {
        r.setIdProva(idProva);
        r.setIdQuestao(idQuestao);
        r.setMatricula(matricula);
        service.atualizar(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<RealizaProva> listarTodasRealizacoes() {
        return service.listar();
    }

    @GetMapping("/avaliacao/{idProva}")
    public List<RealizacaoQuestaoDTO> listarRealizacoesPorAvaliacao(@PathVariable int idProva) {
        return service.listarRealizacoesPorAvaliacao(idProva);
    }

    @GetMapping("/{idProva}/{idQuestao}/{matricula}")
    public ResponseEntity<RealizacaoQuestaoDTO> buscarRealizacaoDetalhada(
            @PathVariable int idProva,
            @PathVariable int idQuestao,
            @PathVariable int matricula) {
        Optional<RealizacaoQuestaoDTO> realizacao = service.buscarRealizacaoDetalhada(idProva, idQuestao, matricula);
        return realizacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProva}/{idQuestao}/{matricula}")
    public ResponseEntity<Void> deletarRealizacao(@PathVariable int idProva, @PathVariable int idQuestao, @PathVariable int matricula) {
        service.deletar(idProva, idQuestao, matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}