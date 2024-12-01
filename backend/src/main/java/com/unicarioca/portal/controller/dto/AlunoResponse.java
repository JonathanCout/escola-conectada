package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private String cpf;
    private EnderecoResponse endereco;
    private String telefone;
    private String ano;
    private Set<AlunoTurmaResponse> boletim;
    private Set<ParenteResponse> responsaveis;

}
