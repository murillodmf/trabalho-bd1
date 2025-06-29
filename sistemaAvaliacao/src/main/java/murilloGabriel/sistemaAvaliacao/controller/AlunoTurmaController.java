package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.model.AlunoTurma;
import murilloGabriel.sistemaAvaliacao.service.AlunoTurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos-turmas")
public class AlunoTurmaController {

    @Autowired
    private AlunoTurmaService service;

    @PostMapping
    public void salvar(@RequestBody AlunoTurma at) {
        service.salvar(at);
    }

    @GetMapping
    public List<AlunoTurma> listar() {
        return service.listar();
    }

    @DeleteMapping("/{matricula}/{codTurma}")
    public void deletar(@PathVariable int matricula, @PathVariable int codTurma) {
        service.deletar(matricula, codTurma);
    }
}