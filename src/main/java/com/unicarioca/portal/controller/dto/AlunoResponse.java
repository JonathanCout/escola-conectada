package com.unicarioca.portal.controller.dto;

import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.entity.Endereco;
import lombok.Data;

import java.util.Set;

@Data
public class AlunoResponse {

    private String nome;
    private String email;
    private String matricula;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private String ano;
    private Set<AlunoTurmaResponse> boletim;
    private Set<ParenteResponse> responsaveis;

}
