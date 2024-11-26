package com.unicarioca.portal.controller.dto;

import lombok.Data;

@Data
public class ParenteRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
}
