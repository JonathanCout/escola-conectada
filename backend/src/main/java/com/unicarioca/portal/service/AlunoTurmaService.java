package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.AlunoTurmaResponse;
import com.unicarioca.portal.controller.dto.DisciplinaRequest;
import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.service.crud.AlunoCrudService;
import com.unicarioca.portal.service.crud.AlunoTurmaCrudService;
import com.unicarioca.portal.service.mapper.AlunoTurmaMapper;
import com.unicarioca.portal.service.mapper.DisciplinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoTurmaService {

    @Autowired
    private AlunoTurmaCrudService alunoTurmaCrudService;

    private final Logger log = LoggerFactory.getLogger(AlunoTurmaService.class);

    public List<AlunoTurmaResponse> getAllByAlunosIds() {
        return alunoTurmaCrudService.getAll().stream().map(AlunoTurmaMapper::toDto).toList();
    }

    public AlunoTurmaResponse saveAlunoTurma(AlunoTurma alunoTurmaRequest) {
        return AlunoTurmaMapper.toDto(alunoTurmaCrudService.saveAlunoTurma(alunoTurmaRequest));
    }

}
