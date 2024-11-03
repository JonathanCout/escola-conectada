package com.unicarioca.portal.controller.dto;

import lombok.Data;

@Data
public class ProfessorRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String matricula;
    private String especialidade;
    private String lattes;
    private EnderecoRequest endereco;

}
