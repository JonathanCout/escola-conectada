package com.unicarioca.portal.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class TurmaResponse {

    private Long id;
    private String nome;
    private String codigo;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    private String sala;
    private String ano;
    private ProfessorResponse professor;
    private Set<AlunoResponse> alunos;
    private DisciplinaResponse disciplina;

}
