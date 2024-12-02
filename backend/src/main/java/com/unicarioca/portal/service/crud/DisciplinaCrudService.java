package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaCrudService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina getDisciplinaById(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId).orElse(null);
    }

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaByNome(String nome) {
        return disciplinaRepository.findByNome(nome).orElse(null);
    }

    public Disciplina getDisciplinaByCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo).orElse(null);
    }

    public Disciplina saveDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
