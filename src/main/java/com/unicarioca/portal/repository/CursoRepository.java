package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
