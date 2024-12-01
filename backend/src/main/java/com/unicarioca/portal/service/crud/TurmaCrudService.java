package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.entity.Turma;
import com.unicarioca.portal.repository.TurmaRepository;
import com.unicarioca.portal.service.mapper.TurmaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurmaCrudService {

    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ProfessorCrudService professorCrudService;
    @Autowired
    private DisciplinaCrudService disciplinaCrudService;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public Turma getTurmaByNome(String nome) {
        return turmaRepository.findByNome(nome);
    }

    public Turma getTurmaByCodigo(String codigo) {
        return turmaRepository.findByCodigo(codigo);
    }

    public Turma getTurmaById(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Turma saveTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Transactional
    public void deleteTurmaById(Long id) {
        turmaRepository.deleteById(id);
    }

    @Transactional
    public Turma updateTurma(Long id, TurmaRequest turmaRequest) {
        Turma turma = getTurmaById(id);
        Professor professor = professorCrudService.getProfessorById(turmaRequest.getProfessorId());
        Disciplina disciplina = disciplinaCrudService.getDisciplinaById(turmaRequest.getDisciplinaId());
        turma.setCodigo(turmaRequest.getCodigo());
        turma.setAno(turmaRequest.getPeriodo());
        turma.setHorario(turmaRequest.getHorario());
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);
        return turmaRepository.save(turma);
    }


}
