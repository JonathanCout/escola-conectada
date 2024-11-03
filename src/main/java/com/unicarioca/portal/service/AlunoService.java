package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.entity.Turma;
import com.unicarioca.portal.service.crud.AlunoCrudService;
import com.unicarioca.portal.service.crud.TurmaCrudService;
import com.unicarioca.portal.service.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private AlunoCrudService alunoCrudService;
    private TurmaCrudService turmaCrudService;

    public void matricularAluno(String matricula, String codigoTurma) {
        Aluno aluno = alunoCrudService.getAlunoByMatricula(matricula);
        if (aluno == null) {
            return;
        }
        Turma turma = turmaCrudService.getTurmaByCodigo(codigoTurma);
        if (turma == null) {
            return;
        }
        if (turma.getAlunoTurma() == null) {
            turma.setAlunoTurma(new HashSet<>());
        }
        turma.getAlunoTurma().add(new AlunoTurma(){{
            setAluno(aluno);
            setTurma(turma);
        }});
        turmaCrudService.saveTurma(turma);
    }

    public void desmatricularAluno(String matricula, String codigoTurma) {
        Aluno aluno = alunoCrudService.getAlunoByMatricula(matricula);
        if (aluno == null) {
            return;
        }
        Turma turma = turmaCrudService.getTurmaByCodigo(codigoTurma);
        if (turma == null) {
            return;
        }
        if (turma.getAlunoTurma() == null) {
            return;
        }
        turma.getAlunoTurma().removeIf(alunoTurma -> alunoTurma.getAluno().getMatricula().equals(matricula));
        turmaCrudService.saveTurma(turma);
    }

    public AlunoResponse getAluno(String matricula, String cpf) {
        Aluno aluno;
        if (matricula != null) {
            aluno = alunoCrudService.getAlunoByMatricula(matricula);
        }
        else {
            aluno = alunoCrudService.getAlunoByCpf(cpf);
        }
        if (aluno == null) {
            return null;
        }
        return AlunoMapper.toDto(aluno);
    }

    public AlunoResponse getAlunoById(Long id) {
        return AlunoMapper.toDto(alunoCrudService.getAlunoById(id));
    }

    public AlunoResponse saveAluno(AlunoRequest alunoRequest) {
        return AlunoMapper.toDto(alunoCrudService.saveAluno(alunoRequest));
    }

    public void deleteAluno(Long id) {
        alunoCrudService.deleteAluno(id);
    }

    public AlunoResponse updateAluno(Long id, AlunoRequest alunoRequest) {
        return AlunoMapper.toDto(alunoCrudService.updateAluno(id, alunoRequest));
    }
}
