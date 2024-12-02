// backend/src/main/java/com/unicarioca/portal/controller/ChatController.java
package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Conversa;
import com.unicarioca.portal.entity.Mensagem;
import com.unicarioca.portal.controller.dto.MensagemRequest; // Importando o DTO
import com.unicarioca.portal.service.ConversaService; // Importando o serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ConversaService conversaService; // Serviço de conversa

    @GetMapping("/conversas/{usuarioId}")
    public ResponseEntity<List<Conversa>> getConversas(@PathVariable Long usuarioId) {
        List<Conversa> conversas = conversaService.getConversasPorUsuario(usuarioId);
        return ResponseEntity.ok(conversas);
    }

    @GetMapping("/conversas/{conversaId}/mensagens")
    public ResponseEntity<List<Mensagem>> getMensagens(@PathVariable Long conversaId) {
        List<Mensagem> mensagens = conversaService.getMensagensPorConversa(conversaId);
        return ResponseEntity.ok(mensagens);
    }

    @PostMapping("/conversas/{conversaId}/mensagens")
    public ResponseEntity<Mensagem> enviarMensagem(@PathVariable Long conversaId, @RequestBody MensagemRequest mensagemRequest) {
        Mensagem mensagem = conversaService.enviarMensagem(conversaId, mensagemRequest);
        return ResponseEntity.ok(mensagem);
    }
}