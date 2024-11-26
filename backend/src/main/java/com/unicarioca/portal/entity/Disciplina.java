package com.unicarioca.portal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;
    private String descricao;
    private String cargaHoraria;
    private String ementa;
    private String bibliografia;
    @OneToMany(mappedBy = "disciplina")
    private Set<Turma> turmas;
}
