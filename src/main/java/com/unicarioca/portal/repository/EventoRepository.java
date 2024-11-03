package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
    Evento findByNome(String nome);
}
