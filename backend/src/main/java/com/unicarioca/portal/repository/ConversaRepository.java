// backend/src/main/java/com/unicarioca/portal/repository/ConversaRepository.java
package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
    List<Conversa> findByUsuarioId(Long usuarioId);
}
