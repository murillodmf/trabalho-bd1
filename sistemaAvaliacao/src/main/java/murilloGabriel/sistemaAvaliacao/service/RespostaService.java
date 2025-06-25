package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.CorrecaoDTO;
import murilloGabriel.sistemaAvaliacao.dto.RespostaEnvioDTO;
import murilloGabriel.sistemaAvaliacao.model.Resposta;
import murilloGabriel.sistemaAvaliacao.repository.AlunoRepository;
import murilloGabriel.sistemaAvaliacao.repository.AvaliacaoRepository;
import murilloGabriel.sistemaAvaliacao.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public void submeterRespostas(Integer idProva, Integer matriculaAluno, List<RespostaEnvioDTO> respostasDTO) {
        // Validação básica para garantir que o aluno e a avaliação existem.
        if (alunoRepository.findById(matriculaAluno).isEmpty() || avaliacaoRepository.findById(idProva).isEmpty()) {
            throw new IllegalArgumentException("Aluno ou Avaliação não encontrado.");
        }

        for (RespostaEnvioDTO dto : respostasDTO) {
            Resposta resposta = new Resposta();
            resposta.setIdProva(idProva);
            resposta.setMatriculaAluno(matriculaAluno);
            resposta.setIdQuestao(dto.getIdQuestao());
            resposta.setRespostaDissertativa(dto.getRespostaTexto());
            resposta.setRespostaObjetiva(dto.getRespostaAlternativa());

            // A lógica de auto-correção para questões objetivas poderia ser adicionada aqui.
            // Por simplicidade, estamos apenas salvando a resposta do aluno.

            respostaRepository.save(resposta);
        }
    }

    public List<Resposta> buscarRespostasDeUmAluno(Integer idProva, Integer matriculaAluno) {
        return respostaRepository.findByProvaAndAluno(idProva, matriculaAluno);
    }

    @Transactional
    public void corrigirResposta(Integer idProva, Integer matriculaAluno, Integer idQuestao, CorrecaoDTO correcaoDTO) {
        respostaRepository.corrigirResposta(
                idProva,
                idQuestao,
                matriculaAluno,
                correcaoDTO.getNota(),
                correcaoDTO.getComentario()
        );
    }
}