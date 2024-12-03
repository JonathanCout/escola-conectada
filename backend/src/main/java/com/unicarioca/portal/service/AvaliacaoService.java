package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.AvaliacaoRequest;
import com.unicarioca.portal.controller.dto.AvaliacaoResponse;
import com.unicarioca.portal.entity.Avaliacao;
import com.unicarioca.portal.service.crud.AvaliacaoCrudService;
import com.unicarioca.portal.service.mapper.AvaliacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoService.class);

    @Autowired
    private AvaliacaoCrudService avaliacaoCrudService;

    public List<AvaliacaoResponse> getAvaliacao(Long alunoId, Long turmaId) {
        List<Avaliacao> avaliacoes;
        if (alunoId != null){
            avaliacoes = avaliacaoCrudService.getAvaliacaoByAlunoId(alunoId);
        }else if (turmaId != null){
            avaliacoes = avaliacaoCrudService.getAvaliacaoByTurmaId(turmaId);
        }else {
            return null;
        }
        return avaliacoes.stream().map(AvaliacaoMapper::toDto).toList();
    }

    public AvaliacaoResponse getAvaliacao(Long id){
        return AvaliacaoMapper.toDto(avaliacaoCrudService.getAvaliacaoById(id));
    }

    public AvaliacaoResponse saveAvaliacao(AvaliacaoRequest avaliacaoRequest) {
        return AvaliacaoMapper.toDto(avaliacaoCrudService.saveAvaliacao(avaliacaoRequest));
    }

    public AvaliacaoResponse updateAvaliacao(Long id, AvaliacaoRequest avaliacaoRequest){
        return AvaliacaoMapper.toDto(avaliacaoCrudService.updateAvaliacao(id, avaliacaoRequest));
    }
    public void deleteAvaliacao(Long id) {
        avaliacaoCrudService.deleteAvaliacao(id);
    }

}
