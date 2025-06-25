package murilloGabriel.sistemaAvaliacao.controller;

import murilloGabriel.sistemaAvaliacao.dto.CorrecaoDTO;
import murilloGabriel.sistemaAvaliacao.dto.RespostaEnvioDTO;
import murilloGabriel.sistemaAvaliacao.model.Resposta;
import murilloGabriel.sistemaAvaliacao.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes/{idProva}") // Base para todas as operações de resposta
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    /**
     * Endpoint para um aluno submeter todas as suas respostas para uma avaliação.
     * @param idProva ID da Avaliação (da URL).
     * @param matriculaAluno ID do Aluno (da URL).
     * @param respostasDTO Lista de respostas enviadas no corpo da requisição.
     * @return Status 201 Created em caso de sucesso.
     */
    @PostMapping("/alunos/{matriculaAluno}/respostas")
    public ResponseEntity<Void> submeterRespostas(@PathVariable Integer idProva,
                                                  @PathVariable Integer matriculaAluno,
                                                  @RequestBody List<RespostaEnvioDTO> respostasDTO) {
        try {
            respostaService.submeterRespostas(idProva, matriculaAluno, respostasDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            // Caso o aluno ou a avaliação não existam
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para buscar todas as respostas de um aluno para uma avaliação.
     * @param idProva ID da Avaliação (da URL).
     * @param matriculaAluno ID do Aluno (da URL).
     * @return Lista de respostas com status 200 OK.
     */
    @GetMapping("/alunos/{matriculaAluno}/respostas")
    public ResponseEntity<List<Resposta>> buscarRespostasDoAluno(@PathVariable Integer idProva,
                                                                 @PathVariable Integer matriculaAluno) {
        List<Resposta> respostas = respostaService.buscarRespostasDeUmAluno(idProva, matriculaAluno);
        return ResponseEntity.ok(respostas);
    }

    /**
     * Endpoint para um professor corrigir uma resposta específica.
     * @param idProva ID da Avaliação (da URL).
     * @param matriculaAluno ID do Aluno (da URL).
     * @param idQuestao ID da Questão (da URL).
     * @param correcaoDTO Dados da correção (nota e comentário) no corpo da requisição.
     * @return Status 200 OK em caso de sucesso.
     */
    @PutMapping("/alunos/{matriculaAluno}/questoes/{idQuestao}/correcao")
    public ResponseEntity<Void> corrigirResposta(@PathVariable Integer idProva,
                                                 @PathVariable Integer matriculaAluno,
                                                 @PathVariable Integer idQuestao,
                                                 @RequestBody CorrecaoDTO correcaoDTO) {
        // A validação se a resposta existe é feita implicitamente pela query de update.
        // Se nenhuma linha for afetada, a operação simplesmente não faz nada no banco.
        // Uma lógica mais robusta poderia verificar a existência antes de chamar o update.
        respostaService.corrigirResposta(idProva, matriculaAluno, idQuestao, correcaoDTO);
        return ResponseEntity.ok().build();
    }
}