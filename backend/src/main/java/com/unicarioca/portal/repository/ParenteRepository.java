package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Parente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ParenteRepository extends JpaRepository<Parente,Long> {

    Parente findByCpf(String cpf);

    @Query(value = "SELECT * FROM Parente p right join Parente_Aluno pa on p.id = pa.parente_id where pa.aluno_id = ?1", nativeQuery = true)
    Set<Parente> findByAlunoId(Long alunoId);
}
