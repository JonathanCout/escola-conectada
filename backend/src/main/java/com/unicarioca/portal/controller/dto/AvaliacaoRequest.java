package com.unicarioca.portal.controller.dto;

import com.unicarioca.portal.util.TipoAvaliacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvaliacaoRequest {

    private String alunoMatricula;
    private String codigoTurma;
    private BigDecimal nota;
    private TipoAvaliacao tipo;
}
