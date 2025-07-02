package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.AvaliacaoDTO;
import murilloGabriel.sistemaAvaliacao.model.Avaliacao;
import murilloGabriel.sistemaAvaliacao.model.AvaliacaoContemQuestao;
import murilloGabriel.sistemaAvaliacao.model.RealizaProva;
import murilloGabriel.sistemaAvaliacao.repository.AvaliacaoContemQuestaoRepository;
import murilloGabriel.sistemaAvaliacao.repository.AvaliacaoRepository;
import murilloGabriel.sistemaAvaliacao.repository.RealizaProvaRepository;
import murilloGabriel.sistemaAvaliacao.repository.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoContemQuestaoRepository avaliacaoContemQuestaoRepository;
    private final RealizaProvaRepository realizaProvaRepository;
    private final TurmaRepository turmaRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository,
                            AvaliacaoContemQuestaoRepository avaliacaoContemQuestaoRepository,
                            RealizaProvaRepository realizaProvaRepository,
                            TurmaRepository turmaRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoContemQuestaoRepository = avaliacaoContemQuestaoRepository;
        this.realizaProvaRepository = realizaProvaRepository;
        this.turmaRepository = turmaRepository;
    }

    @Transactional
    public Integer salvarAvaliacao(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setData(dto.getData());
        avaliacao.setNotaMaxima(dto.getNotaMaxima());
        avaliacao.setCodTurma(dto.getCodTurma());

        Integer idProvaGerado = avaliacaoRepository.salvar(avaliacao);
        if (idProvaGerado == null) {
            throw new RuntimeException("Falha ao salvar avaliação e obter ID.");
        }

        if (dto.getQuestoesIds() != null && !dto.getQuestoesIds().isEmpty()) {
            for (Integer idQuestao : dto.getQuestoesIds()) {
                AvaliacaoContemQuestao acq = new AvaliacaoContemQuestao(idProvaGerado, idQuestao);
                avaliacaoContemQuestaoRepository.salvar(acq);
            }
        }

        List<Integer> matriculasAlunosNaTurma = realizaProvaRepository.buscarMatriculasAlunosPorTurma(dto.getCodTurma());
        if (dto.getQuestoesIds() != null) {
            for (Integer idQuestao : dto.getQuestoesIds()) {
                for (Integer matriculaAluno : matriculasAlunosNaTurma) {
                    RealizaProva rp = new RealizaProva(idProvaGerado, idQuestao, matriculaAluno, null, null);
                    realizaProvaRepository.salvar(rp);
                }
            }
        }

        return idProvaGerado;
    }

    @Transactional
    public void atualizarAvaliacao(int idProva, AvaliacaoDTO dto) {
        Optional<Avaliacao> existingAvaliacaoOpt = avaliacaoRepository.buscar(idProva);
        if (existingAvaliacaoOpt.isEmpty()) {
            throw new RuntimeException("Avaliação não encontrada para atualização: " + idProva);
        }
        Avaliacao existingAvaliacao = existingAvaliacaoOpt.get();

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdProva(idProva);
        avaliacao.setData(dto.getData());
        avaliacao.setNotaMaxima(dto.getNotaMaxima());
        avaliacao.setCodTurma(existingAvaliacao.getCodTurma());

        avaliacaoRepository.atualizar(avaliacao);

        List<Integer> currentQuestaoIdsInDb = avaliacaoContemQuestaoRepository.buscarQuestoesIdsPorProva(idProva);

        List<Integer> questoesRemovidas = currentQuestaoIdsInDb.stream()
                .filter(id -> !dto.getQuestoesIds().contains(id))
                .collect(Collectors.toList());

        List<Integer> novasQuestoesAdicionadas = dto.getQuestoesIds().stream()
                .filter(id -> !currentQuestaoIdsInDb.contains(id))
                .collect(Collectors.toList());

        for (Integer idQuestaoRemovida : questoesRemovidas) {
            avaliacaoContemQuestaoRepository.deletar(idProva, idQuestaoRemovida);
            realizaProvaRepository.deletarRealizacoesPorQuestaoDaProva(idProva, idQuestaoRemovida);
        }

        if (!novasQuestoesAdicionadas.isEmpty()) {
            List<Integer> matriculasAlunosNaTurma = realizaProvaRepository.buscarMatriculasAlunosPorTurma(existingAvaliacao.getCodTurma());
            for (Integer idNovaQuestao : novasQuestoesAdicionadas) {
                avaliacaoContemQuestaoRepository.salvar(new AvaliacaoContemQuestao(idProva, idNovaQuestao));
                for (Integer matriculaAluno : matriculasAlunosNaTurma) {
                    realizaProvaRepository.salvar(new RealizaProva(idProva, idNovaQuestao, matriculaAluno, null, null));
                }
            }
        }
    }

    @Transactional
    public void deletarAvaliacao(int idProva) {
        avaliacaoContemQuestaoRepository.deletarPorProva(idProva);
        realizaProvaRepository.deletarTodasRealizacoesPorProva(idProva);
        avaliacaoRepository.deletar(idProva);
    }

    public List<AvaliacaoDTO> listarAvaliacoesComDetalhes() {
        List<AvaliacaoDTO> avaliacoes = avaliacaoRepository.listarComNomeTurma();
        for (AvaliacaoDTO dto : avaliacoes) {
            List<Integer> questoesIds = avaliacaoContemQuestaoRepository.buscarQuestoesIdsPorProva(dto.getIdProva());
            dto.setQuestoesIds(questoesIds);

            turmaRepository.buscar(dto.getCodTurma()).ifPresent(turma -> {
                dto.setNomeTurma(turma.getMateria());
            });
        }
        return avaliacoes;
    }

    public AvaliacaoDTO buscarAvaliacaoComDetalhes(int idProva) {
        Optional<Avaliacao> optionalAvaliacao = avaliacaoRepository.buscar(idProva);
        if (optionalAvaliacao.isEmpty()) {
            return null;
        }
        Avaliacao avaliacao = optionalAvaliacao.get();
        List<Integer> questoesIds = avaliacaoContemQuestaoRepository.buscarQuestoesIdsPorProva(idProva);

        String nomeTurma = turmaRepository.buscar(avaliacao.getCodTurma())
                .map(turma -> turma.getMateria())
                .orElse("N/A");

        return new AvaliacaoDTO(avaliacao, nomeTurma, questoesIds);
    }
}