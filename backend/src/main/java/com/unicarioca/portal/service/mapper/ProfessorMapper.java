package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;

public class ProfessorMapper {

    public static ProfessorResponse toDto(Professor professor) {
        if (professor == null){
            return null;
        }
        ProfessorResponse professorResponse = new ProfessorResponse();
        professorResponse.setId(professor.getId());
        professorResponse.setNome(professor.getNome());
        professorResponse.setEmail(professor.getEmail());
        professorResponse.setCpf(professor.getCpf());
        professorResponse.setTelefone(professor.getTelefone());
        professorResponse.setEndereco(professor.getEndereco());
        professorResponse.setMatricula(professor.getMatricula());
        professorResponse.setEspecialidade(professor.getEspecialidade());
        professorResponse.setLattes(professor.getLattes());
        return professorResponse;
    }

    public static Professor toEntity(ProfessorRequest professorRequest) {
        if (professorRequest == null){
            return null;
        }
        Professor professor = new Professor();
        professor.setNome(professorRequest.getNome());
        professor.setEmail(professorRequest.getEmail());
        professor.setCpf(professorRequest.getCpf());
        professor.setTelefone(professorRequest.getTelefone());
        professor.setEndereco(EnderecoMapper.toEntity(professorRequest.getEndereco()));
        professor.setMatricula(professorRequest.getMatricula());
        professor.setLattes(professorRequest.getLattes());
        professor.setEspecialidade(professorRequest.getEspecialidade());
        return professor;
    }
}
