// backend/src/main/java/com/unicarioca/portal/controller/dto/MensagemRequest.java
package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensagemRequest {

    private Long remetenteId; // ID do remetente
    private Long conversaId; // ID da conversa
    private LocalDateTime dataEnvio; // Data e hora do envio
    private String conteudo; // Conteúdo da mensagem
}