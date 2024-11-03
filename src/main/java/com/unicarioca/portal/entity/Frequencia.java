package com.unicarioca.portal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private Boolean presente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_turma_id")
    private AlunoTurma alunoTurma;

}
