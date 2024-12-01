package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.Disciplina;

public class DisciplinaMapper {

        public static DisciplinaResponse toDto(Disciplina disciplina) {
            DisciplinaResponse disciplinaResponse = new DisciplinaResponse();
            disciplinaResponse.setId(disciplina.getId());
            disciplinaResponse.setNome(disciplina.getNome());
            disciplinaResponse.setCodigo(disciplina.getCodigo());
            disciplinaResponse.setCargaHoraria(disciplina.getCargaHoraria());
            disciplinaResponse.setEmenta(disciplina.getEmenta());
            disciplinaResponse.setBibliografia(disciplina.getBibliografia());

            return disciplinaResponse;
        }
}
