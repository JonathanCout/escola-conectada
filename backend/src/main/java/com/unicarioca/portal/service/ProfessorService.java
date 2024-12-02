package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.service.crud.ProfessorCrudService;
import com.unicarioca.portal.service.mapper.ProfessorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    @Autowired
    private ProfessorCrudService professorCrudService;

    public ProfessorResponse getProfessor(Long id) {
        return ProfessorMapper.toDto(professorCrudService.getProfessorById(id));
    }

    public ProfessorResponse getProfessor(String cpf, String matricula, String email) {
        return ProfessorMapper.toDto(professorCrudService.getProfessor(cpf, matricula, email));
    }

    public ProfessorResponse saveProfessor(ProfessorRequest professorRequest) throws Exception {
        Professor professor = professorCrudService.getProfessor(professorRequest.getCpf(), professorRequest.getMatricula(), professorRequest.getEmail());
        if (professor != null){
            log.warn("Já existe um professir cadastrado com as informações fornecidas");
            throw new Exception("Já existe um professir cadastrado com as informações fornecidas");
        }

        return ProfessorMapper.toDto(professorCrudService.saveProfessor(ProfessorMapper.toEntity(professorRequest)));
    }

    public void deleteProfessor(Long id) {
        professorCrudService.deleteProfessor(id);
    }

    public List<ProfessorResponse> getAllProfessors() {
        return professorCrudService.getAllProfessors().stream().map(ProfessorMapper::toDto).toList();
    }
}
