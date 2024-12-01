package com.unicarioca.portal.controller.dto;

import lombok.Data;

@Data
public class DisciplinaResponse {

    private Long id;
    private String nome;
    private String codigo;
    private String descricao;
    private String cargaHoraria;
    private String ementa;
    private String bibliografia;
}
