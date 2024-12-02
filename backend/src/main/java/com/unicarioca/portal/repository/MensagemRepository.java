// backend/src/main/java/com/unicarioca/portal/repository/MensagemRepository.java
package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByConversaId(Long conversaId);
}
