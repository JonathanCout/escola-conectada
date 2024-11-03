package com.unicarioca.portal.controller.dto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlunoTurmaResponse {

    @JsonIdentityReference(alwaysAsId = true)
    private AlunoResponse alunoResponse;
    private TurmaResponse turmaResponse;
    private BigDecimal media;
    private BigDecimal frequencia;
}
