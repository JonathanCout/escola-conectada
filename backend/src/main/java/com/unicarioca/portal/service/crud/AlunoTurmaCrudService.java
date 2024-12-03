package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.repository.AlunoTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AlunoTurmaCrudService {

    @Autowired
    private AlunoTurmaRepository alunoTurmaRepository;
    @Autowired
    private TurmaCrudService turmaCrudService;
    @Autowired
    private AlunoCrudService alunoCrudService;

    public List<AlunoTurma> getAll(){
        return alunoTurmaRepository.findAll();
    }

    public AlunoTurma saveAlunoTurma(AlunoTurma alunoTurma){
        alunoTurma.setAluno(alunoCrudService.getAlunoById(alunoTurma.getAluno().getId()));
        alunoTurma.setTurma(turmaCrudService.getTurmaById(alunoTurma.getTurma().getId()));

        return alunoTurmaRepository.save(alunoTurma);
    }
}
