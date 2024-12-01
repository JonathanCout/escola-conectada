package com.unicarioca.portal.controller.dto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlunoTurmaResponse {

    private Long id;
    @JsonIdentityReference(alwaysAsId = true)
    private AlunoResponse alunoResponse;
    private TurmaResponse turmaResponse;
    private BigDecimal media;
    private BigDecimal frequencia;
}
