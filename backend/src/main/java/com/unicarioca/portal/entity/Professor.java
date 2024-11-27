package com.unicarioca.portal.entity;

import com.unicarioca.portal.util.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String especialidade;
    private String lattes;
    private String senha;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    @OneToMany(mappedBy = "professor")
    private Set<Turma> turmas;
}
