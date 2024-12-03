package com.unicarioca.portal.repository;

import com.unicarioca.portal.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.aluno.id = ?1")
    List<Avaliacao> findByAlunoId(Long alunoId);

    @Query("SELECT a FROM Avaliacao a WHERE a.turma.id = ?1")
    List<Avaliacao> findByTurmaId(Long turmaId);
}
