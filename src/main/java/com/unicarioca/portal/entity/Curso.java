package com.unicarioca.portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;
    private String descricao;
    private String cargaHoraria;
    private Turno turno;

    @ManyToMany
    @JoinTable(name = "curso_disciplina",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    private Set<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso")
    private Set<Aluno> alunos;

    public enum Turno {
        MANHA, TARDE, NOITE
    }

}
