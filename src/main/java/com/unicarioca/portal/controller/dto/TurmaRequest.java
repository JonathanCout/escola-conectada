package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class TurmaRequest {

    private String codigo;
    private String periodo;
    private LocalTime horario;
    private Long professorId;
    private Long disciplinaId;
    private Set<Long> alunosId;

}
