package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.QuestaoCompletaDTO;
import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    @Autowired
    private QuestaoService service;

    @PostMapping("/completa")
    public ResponseEntity<Void> salvarQuestaoCompleta(@RequestBody QuestaoCompletaDTO dto) {
        service.salvarQuestaoCompleta(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Questao> listar() {
        return service.listarQuestoesSimples();
    }

    @GetMapping("/completa/{id}")
    public ResponseEntity<QuestaoCompletaDTO> buscarQuestaoCompleta(@PathVariable int id) {
        QuestaoCompletaDTO dto = service.buscarQuestaoCompleta(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/completa/{id}")
    public ResponseEntity<Void> atualizarQuestaoCompleta(@PathVariable int id, @RequestBody QuestaoCompletaDTO dto) {
        service.atualizarQuestaoCompleta(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable int id) {
        service.deletarQuestaoCompleta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}