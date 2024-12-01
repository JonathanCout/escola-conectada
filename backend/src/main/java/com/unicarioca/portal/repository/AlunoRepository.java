package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByMatricula(String matricula);
    Aluno findByCpf(String cpf);
    Aluno findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM parente_aluno where aluno_id = ?1")
    void removeParenteJunction(Long alunoId);
}
