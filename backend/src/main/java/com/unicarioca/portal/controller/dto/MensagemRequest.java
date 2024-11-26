package com.unicarioca.portal.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensagemRequest {

    private Long remetenteId;
    private Long conversaId;
    private LocalDateTime dataEnvio;
    private String conteudo;
}
