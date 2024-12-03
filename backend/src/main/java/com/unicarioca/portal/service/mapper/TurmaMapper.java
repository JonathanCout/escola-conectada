package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import com.unicarioca.portal.entity.*;

import java.util.HashSet;
import java.util.stream.Collectors;

public class TurmaMapper {

    public static Turma toEntity(TurmaRequest turmaRequest) {
        Turma turma = new Turma();
        turma.setCodigo(turmaRequest.getCodigo());
        turma.setAno(turmaRequest.getPeriodo());
        turma.setHorario(turmaRequest.getHorario());
        turma.setProfessor(new Professor(){{setId(turmaRequest.getProfessorId());}});
        turma.setDisciplina(new Disciplina(){{setId(turmaRequest.getDisciplinaId());}});
        turma.setAlunoTurma(turmaRequest.getAlunosId() == null ? new HashSet<>() : turmaRequest.getAlunosId().stream().map(alunoId -> new AlunoTurma(){{setAluno(new Aluno(){{setId(alunoId);}});}}).collect(Collectors.toSet()));
        return turma;
    }

    public static TurmaResponse toDto(Turma turma) {
        TurmaResponse turmaResponse = new TurmaResponse();
        turmaResponse.setId(turmaResponse.getId());
        turmaResponse.setNome(turma.getNome());
        turmaResponse.setCodigo(turma.getCodigo());
        turmaResponse.setAno(turma.getAno());
        turmaResponse.setHorario(turma.getHorario());
        turmaResponse.setSala(turma.getSala());
        turmaResponse.setProfessor(ProfessorMapper.toDto(turma.getProfessor()));
        turmaResponse.setDisciplina(DisciplinaMapper.toDto(turma.getDisciplina()));
        return turmaResponse;
    }
}
