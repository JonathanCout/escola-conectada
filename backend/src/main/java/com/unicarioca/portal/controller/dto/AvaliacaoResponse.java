package com.unicarioca.portal.controller.dto;

import com.unicarioca.portal.util.TipoAvaliacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvaliacaoResponse {

    private Long id;
    private TipoAvaliacao tipo;
    private AlunoResponse aluno;
    private TurmaResponse turma;
    private BigDecimal nota;
}
