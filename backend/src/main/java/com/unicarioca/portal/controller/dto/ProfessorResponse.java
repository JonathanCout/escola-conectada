package com.unicarioca.portal.controller.dto;

import com.unicarioca.portal.entity.Endereco;
import lombok.Data;

@Data
public class ProfessorResponse {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String lattes;
    private Endereco endereco;
    private String matricula;
    private String especialidade;
}
