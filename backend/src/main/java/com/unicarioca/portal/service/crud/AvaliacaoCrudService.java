package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.AvaliacaoRequest;
import com.unicarioca.portal.entity.Avaliacao;
import com.unicarioca.portal.repository.AvaliacaoRepository;
import com.unicarioca.portal.service.mapper.AvaliacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoCrudService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private AlunoCrudService alunoCrudService;
    @Autowired
    private TurmaCrudService turmaCrudService;

    public Avaliacao getAvaliacaoById(Long id){
        return avaliacaoRepository.findById(id).orElse(null);
    }

    public Avaliacao getAvaliacaoByAlunoMatricula(String matricula){
        return avaliacaoRepository.findByAlunoMatricula(matricula);
    }

    public Avaliacao getAvaliacaoByTurmaCodigo(String codigo){
        return avaliacaoRepository.findByTurmaCodigo(codigo);
    }

    @Transactional
    public Avaliacao saveAvaliacao(AvaliacaoRequest avaliacaoRequesta){
        Avaliacao avaliacao = AvaliacaoMapper.toEntity(avaliacaoRequesta);
        avaliacao.setTurma(turmaCrudService.getTurmaByCodigo(avaliacaoRequesta.getCodigoTurma()));
        avaliacao.setAluno(alunoCrudService.getAlunoByMatricula(avaliacaoRequesta.getAlunoMatricula()));

        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public void deleteAvaliacao(Long id){
        avaliacaoRepository.deleteById(id);
    }

    @Transactional
    public Avaliacao updateAvaliacao(Long id, AvaliacaoRequest avaliacaoRequest){
        Avaliacao avaliacao = getAvaliacaoById(id);
        if(avaliacao == null){
            return null;
        }

        avaliacao.setNota(avaliacaoRequest.getNota());

        return avaliacaoRepository.save(avaliacao);
    }


}
