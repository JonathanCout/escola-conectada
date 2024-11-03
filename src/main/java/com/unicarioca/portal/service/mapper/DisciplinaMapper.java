package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.entity.Disciplina;

import java.util.stream.Collectors;

public class DisciplinaMapper {

        public static DisciplinaResponse toDto(Disciplina disciplina) {
            DisciplinaResponse response = new DisciplinaResponse();
            response.setNome(disciplina.getNome());
            response.setCodigo(disciplina.getCodigo());
            response.setCargaHoraria(disciplina.getCargaHoraria());
            response.setEmenta(disciplina.getEmenta());
            response.setBibliografia(disciplina.getBibliografia());

            return response;
        }
}
