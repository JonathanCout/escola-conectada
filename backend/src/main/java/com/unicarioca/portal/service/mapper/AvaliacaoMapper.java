package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.AvaliacaoRequest;
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
}
