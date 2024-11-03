package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ConversaRequest {

    private Long alunoId;
    private Long professorId;
    private MensagemRequest mensagens;
}
