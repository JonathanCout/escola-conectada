package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.AvaliacaoRequest;
import com.unicarioca.portal.entity.Avaliacao;
import com.unicarioca.portal.repository.AvaliacaoRepository;
import com.unicarioca.portal.service.mapper.AvaliacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliacaoCrudService {

    private AvaliacaoRepository avaliacaoRepository;
    private AlunoCrudService alunoCrudService;
    private TurmaCrudService turmaCrudService;

    public Avaliacao saveAvaliacao(AvaliacaoRequest avaliacaoRequesta){
        Avaliacao avaliacao = AvaliacaoMapper.toEntity(avaliacaoRequesta);
        avaliacao.setTurma(turmaCrudService.getTurmaByCodigo(avaliacaoRequesta.getCodigoTurma()));
        avaliacao.setAluno(alunoCrudService.getAlunoByMatricula(avaliacaoRequesta.getAlunoMatricula()));

        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao getAvaliacaoById(Long id){
        return avaliacaoRepository.findById(id).orElse(null);
    }

    public Avaliacao getAvaliacaoByAlunoMatricula(String matricula){
        return avaliacaoRepository.findByAlunoMatricula(matricula);
    }

    public Avaliacao getAvaliacaoByTurmaCodigo(String codigo){
        return avaliacaoRepository.findByTurmaCodigo(codigo);
    }

    public void deleteAvaliacao(Long id){
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao updateAvaliacao(Long id, AvaliacaoRequest avaliacaoRequest){
        Avaliacao avaliacao = getAvaliacaoById(id);
        if(avaliacao == null){
            return null;
        }

        avaliacao.setNota(avaliacaoRequest.getNota());

        return avaliacaoRepository.save(avaliacao);
    }


}
