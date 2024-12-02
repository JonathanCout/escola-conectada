package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.DisciplinaRequest;
import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.service.crud.DisciplinaCrudService;
import com.unicarioca.portal.service.mapper.DisciplinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final Logger log = LoggerFactory.getLogger(DisciplinaService.class);

    @Autowired
    private DisciplinaCrudService disciplinaCrudService;


    public List<DisciplinaResponse> getAllDisciplinas() {
        return disciplinaCrudService.getAllDisciplinas().stream().map(DisciplinaMapper::toDto).toList();
    }

    public DisciplinaResponse getDisciplina(String nome, String codigo) {
        Disciplina disciplina = null;
        if (nome != null){
            disciplina = disciplinaCrudService.getDisciplinaByNome(nome);
        }else if (codigo != null){
            disciplina = disciplinaCrudService.getDisciplinaByCodigo(codigo);
        }
        return DisciplinaMapper.toDto(disciplina);
    }

    public DisciplinaResponse getDisciplina(Long id){
        return DisciplinaMapper.toDto(disciplinaCrudService.getDisciplinaById(id));
    }

    public DisciplinaResponse saveDisciplina(DisciplinaRequest disciplinaRequest) {
        return DisciplinaMapper.toDto(disciplinaCrudService.saveDisciplina(DisciplinaMapper.toEntity(disciplinaRequest)));
    }

    public void deleteDisciplina(Long id) {
        disciplinaCrudService.deleteDisciplina(id);
    }
}
