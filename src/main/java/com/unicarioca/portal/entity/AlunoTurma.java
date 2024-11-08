package com.unicarioca.portal.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
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
