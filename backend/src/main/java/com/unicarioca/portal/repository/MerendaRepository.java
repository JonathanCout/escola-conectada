package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Merenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerendaRepository extends JpaRepository<Merenda,Long> {
    Merenda findByNome(String nome);
}
