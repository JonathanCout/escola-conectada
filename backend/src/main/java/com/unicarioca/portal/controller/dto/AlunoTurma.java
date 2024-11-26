package com.unicarioca.portal.controller.dto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

@Data
public class AlunoTurma {

    @JsonIdentityReference(alwaysAsId = true)
    private AlunoResponse alunoResponse;
    private TurmaResponse turmaResponse;
    private Double media;
    private Double frequencia;

}
