package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem,Long> {
}
