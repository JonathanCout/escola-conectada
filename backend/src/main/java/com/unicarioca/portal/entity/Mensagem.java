// backend/src/main/java/com/unicarioca/portal/entity/Mensagem.java
package com.unicarioca.portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversa_id")
    private Conversa conversa;

    private Long remetenteId; // ID do usuário que enviou a mensagem
    private String conteudo;
    private LocalDateTime dataEnvio;
}
