// backend/src/main/java/com/unicarioca/portal/repository/ConversaRepository.java
package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {

    @Query("SELECT c FROM Conversa c WHERE c.aluno.id = :usuarioId OR c.professor.id = :usuarioId")
    List<Conversa> findByUsuarioId(Long usuarioId);
}
