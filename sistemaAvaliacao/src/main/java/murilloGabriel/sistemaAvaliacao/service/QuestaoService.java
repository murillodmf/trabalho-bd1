package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.QuestaoCompletaDTO;
import murilloGabriel.sistemaAvaliacao.model.Dissertativa;
import murilloGabriel.sistemaAvaliacao.model.Objetiva;
import murilloGabriel.sistemaAvaliacao.model.ObjetivaAlternativas;
import murilloGabriel.sistemaAvaliacao.model.Questao;
import murilloGabriel.sistemaAvaliacao.repository.DissertativaRepository;
import murilloGabriel.sistemaAvaliacao.repository.ObjetivaAlternativasRepository;
import murilloGabriel.sistemaAvaliacao.repository.ObjetivaRepository;
import murilloGabriel.sistemaAvaliacao.repository.QuestaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoService {
    private final QuestaoRepository questaoRepository;
    private final ObjetivaRepository objetivaRepository;
    private final DissertativaRepository dissertativaRepository;
    private final ObjetivaAlternativasRepository objetivaAlternativasRepository;

    public QuestaoService(QuestaoRepository questaoRepository,
                          ObjetivaRepository objetivaRepository,
                          DissertativaRepository dissertativaRepository,
                          ObjetivaAlternativasRepository objetivaAlternativasRepository) {
        this.questaoRepository = questaoRepository;
        this.objetivaRepository = objetivaRepository;
        this.dissertativaRepository = dissertativaRepository;
        this.objetivaAlternativasRepository = objetivaAlternativasRepository;
    }

    @Transactional
    public void salvarQuestaoCompleta(QuestaoCompletaDTO dto) {
        Questao questao = new Questao();
        questao.setEnunciado(dto.getEnunciado());
        questao.setTipo(dto.getTipo());
        questaoRepository.salvar(questao);

        if ("OBJETIVA".equals(dto.getTipo())) {
            Objetiva objetiva = new Objetiva();
            objetiva.setIdQuestao(questao.getIdQuestao());
            objetiva.setRespostaCorreta(dto.getRespostaCorreta());
            objetivaRepository.salvar(objetiva);

            if (dto.getAlternativas() != null) {
                for (String alt : dto.getAlternativas()) {
                    ObjetivaAlternativas oa = new ObjetivaAlternativas(questao.getIdQuestao(), alt);
                    objetivaAlternativasRepository.salvar(oa);
                }
            }
        } else if ("DISSERTATIVA".equals(dto.getTipo())) {
            Dissertativa dissertativa = new Dissertativa();
            dissertativa.setIdQuestao(questao.getIdQuestao());
            dissertativa.setRespostaModelo(dto.getRespostaModelo());
            dissertativaRepository.salvar(dissertativa);
        }
    }

    @Transactional
    public QuestaoCompletaDTO buscarQuestaoCompleta(int id) {
        Optional<Questao> optionalQuestao = questaoRepository.buscar(id);
        if (optionalQuestao.isEmpty()) {
            return null;
        }
        Questao questao = optionalQuestao.get();
        QuestaoCompletaDTO dto = new QuestaoCompletaDTO();
        dto.setIdQuestao(questao.getIdQuestao());
        dto.setEnunciado(questao.getEnunciado());
        dto.setTipo(questao.getTipo());

        if ("OBJETIVA".equals(questao.getTipo())) {
            objetivaRepository.buscarPorIdQuestao(questao.getIdQuestao()).ifPresent(objetiva -> {
                dto.setRespostaCorreta(objetiva.getRespostaCorreta());
            });
            List<String> alternativas = objetivaAlternativasRepository.buscarAlternativasPorIdQuestao(questao.getIdQuestao());
            dto.setAlternativas(alternativas);
        } else if ("DISSERTATIVA".equals(questao.getTipo())) {
            dissertativaRepository.buscarPorIdQuestao(questao.getIdQuestao()).ifPresent(dissertativa -> {
                dto.setRespostaModelo(dissertativa.getRespostaModelo());
            });
        }
        return dto;
    }

    @Transactional
    public void atualizarQuestaoCompleta(int id, QuestaoCompletaDTO dto) {
        Questao questao = new Questao();
        questao.setIdQuestao(id);
        questao.setEnunciado(dto.getEnunciado());
        questao.setTipo(dto.getTipo());
        questaoRepository.atualizar(questao);

        if ("OBJETIVA".equals(dto.getTipo())) {
            dissertativaRepository.deletar(id);

            Objetiva objetiva = new Objetiva();
            objetiva.setIdQuestao(id);
            objetiva.setRespostaCorreta(dto.getRespostaCorreta());

            objetivaRepository.buscarPorIdQuestao(id).ifPresentOrElse(
                obj -> objetivaRepository.atualizar(objetiva),
                () -> objetivaRepository.salvar(objetiva)
            );

            objetivaAlternativasRepository.deletarTodasDeUmaQuestao(id);
            if (dto.getAlternativas() != null) {
                for (String alt : dto.getAlternativas()) {
                    ObjetivaAlternativas oa = new ObjetivaAlternativas(id, alt);
                    objetivaAlternativasRepository.salvar(oa);
                }
            }
        } else if ("DISSERTATIVA".equals(dto.getTipo())) {
            objetivaAlternativasRepository.deletarTodasDeUmaQuestao(id);
            objetivaRepository.deletar(id);

            Dissertativa dissertativa = new Dissertativa();
            dissertativa.setIdQuestao(id);
            dissertativa.setRespostaModelo(dto.getRespostaModelo());

            dissertativaRepository.buscarPorIdQuestao(id).ifPresentOrElse(
                dis -> dissertativaRepository.atualizar(dissertativa),
                () -> dissertativaRepository.salvar(dissertativa)
            );
        }
    }

    @Transactional
    public void deletarQuestaoCompleta(int id) {
        Optional<Questao> optionalQuestao = questaoRepository.buscar(id);
        if (optionalQuestao.isPresent()) {
            Questao questao = optionalQuestao.get();
            if ("OBJETIVA".equals(questao.getTipo())) {
                objetivaAlternativasRepository.deletarTodasDeUmaQuestao(id);
                objetivaRepository.deletar(id);
            } else if ("DISSERTATIVA".equals(questao.getTipo())) {
                dissertativaRepository.deletar(id);
            }
        }
        questaoRepository.deletar(id);
    }

    public List<Questao> listarQuestoesSimples() {
        return questaoRepository.listar();
    }
}
