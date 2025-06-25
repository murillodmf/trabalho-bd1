package murilloGabriel.sistemaAvaliacao.service;

import murilloGabriel.sistemaAvaliacao.dto.QuestaoDTO;
import murilloGabriel.sistemaAvaliacao.model.*;
import murilloGabriel.sistemaAvaliacao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestaoService {

    @Autowired private QuestaoRepository questaoRepository;
    @Autowired private ObjetivaRepository objetivaRepository;
    @Autowired private DissertativaRepository dissertativaRepository;

    @Transactional // Garante que ou tudo é salvo, ou nada é (consistência do banco)
    public QuestaoDTO criarQuestao(QuestaoDTO dto) {
        // 1. Salva a questão base
        Questao questao = new Questao();
        questao.setEnunciado(dto.getEnunciado());
        questao.setTipo(dto.getTipo());
        Questao questaoSalva = questaoRepository.save(questao);

        Integer id = questaoSalva.getId();
        dto.setId(id);

        // 2. Salva a parte específica (objetiva ou dissertativa)
        if ("objetiva".equalsIgnoreCase(dto.getTipo())) {
            Objetiva obj = new Objetiva();
            obj.setIdQuestao(id);
            // Pega as 5 primeiras alternativas da lista para preencher A, B, C, D, E
            List<String> alts = dto.getAlternativas();
            obj.setAlternativaA(alts.size() > 0 ? alts.get(0) : null);
            obj.setAlternativaB(alts.size() > 1 ? alts.get(1) : null);
            obj.setAlternativaC(alts.size() > 2 ? alts.get(2) : null);
            obj.setAlternativaD(alts.size() > 3 ? alts.get(3) : null);
            obj.setAlternativaE(alts.size() > 4 ? alts.get(4) : null);
            obj.setRespostaCorreta(dto.getRespostaCorreta());
            objetivaRepository.save(obj);
        } else if ("dissertativa".equalsIgnoreCase(dto.getTipo())) {
            Dissertativa diss = new Dissertativa();
            diss.setIdQuestao(id);
            diss.setRespostaModelo(dto.getRespostaModelo());
            dissertativaRepository.save(diss);
        } else {
            throw new IllegalArgumentException("Tipo de questão inválido: " + dto.getTipo());
        }

        return dto;
    }

    public Optional<QuestaoDTO> buscarPorId(Integer id) {
        Optional<Questao> questaoOpt = questaoRepository.findById(id);
        if (questaoOpt.isEmpty()) {
            return Optional.empty();
        }

        Questao questao = questaoOpt.get();
        QuestaoDTO dto = new QuestaoDTO();
        dto.setId(questao.getId());
        dto.setEnunciado(questao.getEnunciado());
        dto.setTipo(questao.getTipo());

        if ("objetiva".equalsIgnoreCase(questao.getTipo())) {
            objetivaRepository.findById(id).ifPresent(obj -> {
                List<String> alternativas = new ArrayList<>();
                if (obj.getAlternativaA() != null) alternativas.add(obj.getAlternativaA());
                if (obj.getAlternativaB() != null) alternativas.add(obj.getAlternativaB());
                if (obj.getAlternativaC() != null) alternativas.add(obj.getAlternativaC());
                if (obj.getAlternativaD() != null) alternativas.add(obj.getAlternativaD());
                if (obj.getAlternativaE() != null) alternativas.add(obj.getAlternativaE());
                dto.setAlternativas(alternativas);
                dto.setRespostaCorreta(obj.getRespostaCorreta());
            });
        } else if ("dissertativa".equalsIgnoreCase(questao.getTipo())) {
            dissertativaRepository.findById(id).ifPresent(diss -> {
                dto.setRespostaModelo(diss.getRespostaModelo());
            });
        }
        return Optional.of(dto);
    }

    public List<QuestaoDTO> listarTodas() {
        return questaoRepository.findAll().stream()
                .map(q -> buscarPorId(q.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Integer id) {
        // Graças ao "ON DELETE CASCADE" no SQL, ao deletar a questão,
        // o registro correspondente em 'objetiva' ou 'dissertativa' será deletado automaticamente.
        questaoRepository.deleteById(id);
    }
}