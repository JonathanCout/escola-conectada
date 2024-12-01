package com.unicarioca.portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    private Turma turma;
    @OneToMany(mappedBy = "alunoTurma")
    private Set<Frequencia> frequencia;
    private BigDecimal media;



}
