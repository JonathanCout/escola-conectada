// backend/src/main/java/com/unicarioca/portal/service/ConversaService.java
package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.MensagemRequest;
import com.unicarioca.portal.entity.Conversa;
import com.unicarioca.portal.entity.Mensagem;
import com.unicarioca.portal.repository.ConversaRepository;
import com.unicarioca.portal.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime; // Importação necessária
import java.util.List;


@Service
public class ConversaService {

    @Autowired
    private ConversaRepository conversaRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Conversa> getConversasPorUsuario(Long usuarioId) {
        // Lógica para obter conversas do usuário (aluno ou professor)
        return conversaRepository.findByUsuarioId(usuarioId);
    }

    public List<Mensagem> getMensagensPorConversa(Long conversaId) {
        // Lógica para obter mensagens de uma conversa específica
        return mensagemRepository.findByConversaId(conversaId);
    }

    @Transactional
    public Mensagem enviarMensagem(Long conversaId, MensagemRequest mensagemRequest) {
        // Lógica para enviar uma nova mensagem
        Conversa conversa = conversaRepository.findById(conversaId).orElseThrow(() -> new RuntimeException("Conversa não encontrada"));
        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(mensagemRequest.getConteudo());
        mensagem.setRemetenteId(mensagemRequest.getRemetenteId());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setConversa(conversa);
        return mensagemRepository.save(mensagem);
    }
}