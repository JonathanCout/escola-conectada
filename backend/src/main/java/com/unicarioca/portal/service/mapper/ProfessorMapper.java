package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;

public class ProfessorMapper {

    public static ProfessorResponse toDto(Professor professor) {
        ProfessorResponse response = new ProfessorResponse();
        response.setNome(professor.getNome());
        response.setEmail(professor.getEmail());
        response.setCpf(professor.getCpf());
        response.setTelefone(professor.getTelefone());
        response.setEndereco(professor.getEndereco());
        response.setMatricula(professor.getMatricula());
        response.setEspecialidade(professor.getEspecialidade());
        return response;
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
