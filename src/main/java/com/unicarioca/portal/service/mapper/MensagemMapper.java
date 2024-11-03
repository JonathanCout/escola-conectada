package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.MensagemRequest;
import com.unicarioca.portal.entity.Conversa;
import com.unicarioca.portal.entity.Mensagem;

public class MensagemMapper {

    public static Mensagem toEntity(MensagemRequest mensagemRequest) {
        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(mensagemRequest.getConteudo());
        mensagem.setRemetenteId(mensagemRequest.getRemetenteId());
        mensagem.setDataEnvio(mensagemRequest.getDataEnvio());
        mensagem.setConversa(new Conversa(){{setId(mensagemRequest.getConversaId());}});
        return mensagem;
    }
}
