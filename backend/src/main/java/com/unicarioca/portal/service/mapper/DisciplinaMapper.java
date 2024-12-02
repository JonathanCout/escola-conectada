package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.DisciplinaRequest;
import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.Disciplina;

public class DisciplinaMapper {

        public static DisciplinaResponse toDto(Disciplina disciplina) {

            if (disciplina == null){
                return null;
            }
            DisciplinaResponse disciplinaResponse = new DisciplinaResponse();
            disciplinaResponse.setId(disciplina.getId());
            disciplinaResponse.setNome(disciplina.getNome());
            disciplinaResponse.setCodigo(disciplina.getCodigo());
            disciplinaResponse.setCargaHoraria(disciplina.getCargaHoraria());
            disciplinaResponse.setEmenta(disciplina.getEmenta());
            disciplinaResponse.setBibliografia(disciplina.getBibliografia());

            return disciplinaResponse;
        }

        public static Disciplina toEntity(DisciplinaRequest disciplinaRequest){
            if (disciplinaRequest == null){
                return null;
            }
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(disciplinaRequest.getNome());
            disciplina.setCodigo(disciplinaRequest.getCodigo());
            disciplina.setDescricao(disciplinaRequest.getDescricao());
            disciplina.setCargaHoraria(disciplinaRequest.getCargaHoraria());
            disciplina.setEmenta(disciplinaRequest.getEmenta());
            disciplina.setBibliografia(disciplinaRequest.getBibliografia());

            return disciplina;
        }
}
