package com.unicarioca.portal.controller.dto;

import lombok.Data;

@Data
public class ConversaRequest {

    private Long alunoId;
    private Long professorId;
    private MensagemRequest mensagens;
}
