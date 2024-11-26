package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Turma findByCodigo(String codigo);

    Turma findByNome(String nome);
}
