package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByNome(String nome);

    Optional<Disciplina> findByCodigo(String codigo);
}
