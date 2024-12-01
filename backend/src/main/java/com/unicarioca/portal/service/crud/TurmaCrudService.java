package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.entity.Turma;
import com.unicarioca.portal.repository.TurmaRepository;
import com.unicarioca.portal.service.mapper.TurmaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaCrudService {

    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ProfessorCrudService professorCrudService;
    @Autowired
    private DisciplinaCrudService disciplinaCrudService;

    public Turma getTurmaByCodigo(String codigo) {
        return turmaRepository.findByCodigo(codigo);
    }

    public Turma saveTurma(TurmaRequest turmaRequest) {
        Professor professor = professorCrudService.getProfessorById(turmaRequest.getProfessorId());
        Disciplina disciplina = disciplinaCrudService.getDisciplinaById(turmaRequest.getDisciplinaId());
        Turma turma = TurmaMapper.toEntity(turmaRequest);
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);

        return turmaRepository.save(turma);
    }

    public Turma saveTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deleteTurmaById(Long id) {
        turmaRepository.deleteById(id);
    }

    public Turma getTurmaById(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

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

    public Turma getTurmaByNome(String nome) {
        return turmaRepository.findByNome(nome);
    }

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }
}
