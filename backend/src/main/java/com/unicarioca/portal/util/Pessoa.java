package com.unicarioca.portal.util;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
