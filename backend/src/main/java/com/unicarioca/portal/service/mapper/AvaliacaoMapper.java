package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.AvaliacaoRequest;
import com.unicarioca.portal.controller.dto.AvaliacaoResponse;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Avaliacao;
import com.unicarioca.portal.entity.Turma;

public class AvaliacaoMapper {

    public static Avaliacao toEntity(AvaliacaoRequest avaliacaoRequest){
        if (avaliacaoRequest == null) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoRequest.getNota());
        avaliacao.setTipo(avaliacaoRequest.getTipo());
        avaliacao.setAluno(new Aluno(){{setMatricula(avaliacaoRequest.getAlunoMatricula());}});
        avaliacao.setTurma(new Turma(){{setCodigo(avaliacaoRequest.getCodigoTurma());}});
        return avaliacao;

    }

    public static AvaliacaoResponse toDto(Avaliacao avaliacao){
        if (avaliacao == null) {
            return null;
        }

        AvaliacaoResponse response = new AvaliacaoResponse();
        response.setNota(avaliacao.getNota());
        response.setTipo(avaliacao.getTipo());
        response.setAluno(AlunoMapper.toDto(avaliacao.getAluno()));
        response.setTurma(TurmaMapper.toDto(avaliacao.getTurma()));
        return response;

    }
}
