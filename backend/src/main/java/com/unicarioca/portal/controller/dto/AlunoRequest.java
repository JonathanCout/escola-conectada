package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AlunoRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String matricula;
    private String ano;
    private EnderecoRequest endereco;
    private Set<ParenteRequest> responsaveis;

}
