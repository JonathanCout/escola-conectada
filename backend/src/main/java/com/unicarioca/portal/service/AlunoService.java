package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.controller.dto.LoginRequest;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.entity.Turma;
import com.unicarioca.portal.service.crud.AlunoCrudService;
import com.unicarioca.portal.service.crud.TurmaCrudService;
import com.unicarioca.portal.service.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AlunoService {

    private final Logger log = LoggerFactory.getLogger(AlunoService.class);
    @Autowired
    private AlunoCrudService alunoCrudService;
    @Autowired
    private TurmaCrudService turmaCrudService;

    public AlunoResponse getAlunos(String cpf, String matricula, String email) {
        Aluno aluno;
        if (cpf != null) {
            aluno = alunoCrudService.getAlunoByCpf(cpf);
        } else if (matricula != null) {
            aluno = alunoCrudService.getAlunoByMatricula(matricula);
        } else if (email != null) {
            aluno = alunoCrudService.getAlunoByEmail(email);
        } else {
            return null;
        }
        return AlunoMapper.toDto(aluno);
    }

    public List<AlunoResponse> getAllAlunos(){
        List<Aluno> alunos = alunoCrudService.getAllAlunos();
        return alunos.stream().map(AlunoMapper::toDto).toList();
    }

    public AlunoResponse getAlunoById(Long id) {
        return AlunoMapper.toDto(alunoCrudService.getAlunoById(id));
    }

    public AlunoResponse saveAluno(AlunoRequest alunoRequest) throws Exception {
        return AlunoMapper.toDto(alunoCrudService.saveAluno(alunoRequest));
    }

    public void deleteAluno(Long id) {
        alunoCrudService.deleteAluno(id);
    }

    public AlunoResponse updateAluno(Long id, AlunoRequest alunoRequest) {
        return AlunoMapper.toDto(alunoCrudService.updateAluno(id, alunoRequest));
    }

}
