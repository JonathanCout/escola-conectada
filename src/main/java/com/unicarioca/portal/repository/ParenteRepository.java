package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Parente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParenteRepository extends JpaRepository<Parente,Long> {

    Parente findByCpf(String cpf);
}
