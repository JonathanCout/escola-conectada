package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;

public class ProfessorMapper {

    public static ProfessorResponse toDto(Professor professor) {
        ProfessorResponse professorResponse = new ProfessorResponse();
        professorResponse.setId(professor.getId());
        professorResponse.setNome(professor.getNome());
        professorResponse.setEmail(professor.getEmail());
        professorResponse.setCpf(professor.getCpf());
        professorResponse.setTelefone(professor.getTelefone());
        professorResponse.setEndereco(professor.getEndereco());
        professorResponse.setMatricula(professor.getMatricula());
        professorResponse.setEspecialidade(professor.getEspecialidade());
        return professorResponse;
    }

    public static Professor toEntity(ProfessorRequest professorRequest) {
        Professor professor = new Professor();
        professor.setNome(professorRequest.getNome());
        professor.setEmail(professorRequest.getEmail());
        professor.setCpf(professorRequest.getCpf());
        professor.setTelefone(professorRequest.getTelefone());
        professor.setEndereco(EnderecoMapper.toEntity(professorRequest.getEndereco()));
        professor.setMatricula(professorRequest.getMatricula());
        professor.setEspecialidade(professorRequest.getEspecialidade());
        return professor;
    }
}
