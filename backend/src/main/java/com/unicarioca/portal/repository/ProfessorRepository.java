package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByMatricula(String matricula);

    Professor findByCpf(String cpf);

    Professor findByEmail(String email);
}
