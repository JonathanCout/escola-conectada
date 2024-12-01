package com.unicarioca.portal.entity;

import com.unicarioca.portal.util.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private String ano;
    private String matricula;
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "parente_aluno",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "parente_id"))
    private Set<Parente> responsaveis;

    @OneToMany(mappedBy = "aluno")
    private Set<AlunoTurma> turmas;

    @OneToMany(mappedBy = "aluno")
    private Set<Avaliacao> avaliacoes;

}
