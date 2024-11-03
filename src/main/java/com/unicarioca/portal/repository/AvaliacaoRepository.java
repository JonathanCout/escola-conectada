package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.aluno.matricula = ?1")
    Avaliacao findByAlunoMatricula(String matricula);

    @Query("SELECT a FROM Avaliacao a WHERE a.turma.codigo = ?1")
    Avaliacao findByTurmaCodigo(String codigo);
}
