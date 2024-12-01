package com.unicarioca.portal.controller.dto;

import lombok.Data;

@Data
public class ParenteResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
