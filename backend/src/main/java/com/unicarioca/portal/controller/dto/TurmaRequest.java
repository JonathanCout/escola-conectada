package com.unicarioca.portal.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class TurmaRequest {

    private String codigo;
    private String periodo;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    private Long professorId;
    private Long disciplinaId;
    private Set<Long> alunosId;

}
