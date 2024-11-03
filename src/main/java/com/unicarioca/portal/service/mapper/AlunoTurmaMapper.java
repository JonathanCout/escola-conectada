package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.AlunoTurmaResponse;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.entity.Frequencia;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AlunoTurmaMapper {

    public static AlunoTurmaResponse toDto(AlunoTurma alunoTurma) {
        AlunoTurmaResponse alunoTurmaResponse = new AlunoTurmaResponse();
        alunoTurmaResponse.setAlunoResponse(AlunoMapper.toDto(alunoTurma.getAluno()));
        alunoTurmaResponse.setTurmaResponse(TurmaMapper.toDto(alunoTurma.getTurma()));
        alunoTurmaResponse.setMedia(alunoTurma.getMedia());
        long presente = alunoTurma.getFrequencia().stream().filter(Frequencia::getPresente).count();
        BigDecimal presenca = BigDecimal.valueOf(presente).divide(BigDecimal.valueOf(alunoTurma.getFrequencia().size()), 2, RoundingMode.HALF_UP);
        alunoTurmaResponse.setFrequencia(presenca);
        return alunoTurmaResponse;
    }



}
