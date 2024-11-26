package com.unicarioca.portal.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
}
