package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByMatricula(String matricula);
    Aluno findByCpf(String cpf);
    Aluno findByEmail(String email);
}
