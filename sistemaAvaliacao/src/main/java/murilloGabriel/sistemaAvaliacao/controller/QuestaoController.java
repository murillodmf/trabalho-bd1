package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.service.QuestaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    private final QuestaoService questaoService = new QuestaoService();

    @GetMapping
    public List<Questao> listar() {
        return questaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Questao buscar(@PathVariable Integer id) {
        return questaoService.buscarPorId(id);
    }

    @PostMapping
    public Questao criar(@RequestBody Questao questao) {
        return questaoService.salvar(questao);
    }

    @PutMapping("/{id}")
    public Questao atualizar(@PathVariable Integer id, @RequestBody Questao questao) {
        questao.setId(id);
        return questaoService.salvar(questao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        questaoService.deletar(id);
    }
}
