package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.service.crud.ProfessorCrudService;
import com.unicarioca.portal.service.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private ProfessorCrudService professorCrudService;

    public ProfessorResponse getProfessor(Long id) {
        return ProfessorMapper.toDto(professorCrudService.getProfessorById(id));
    }

    public ProfessorResponse getProfessor(String cpf, String matricula) {
        return ProfessorMapper.toDto(professorCrudService.getProfessor(cpf, matricula));
    }


    public ProfessorResponse saveProfessor(ProfessorRequest professorRequest) {
        return ProfessorMapper.toDto(professorCrudService.saveProfessor(ProfessorMapper.toEntity(professorRequest)));
    }

    public void deleteProfessor(Long id) {
        professorCrudService.deleteProfessor(id);
    }
}
