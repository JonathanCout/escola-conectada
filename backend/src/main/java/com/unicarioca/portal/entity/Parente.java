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
public class Parente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "parente_aluno",
               joinColumns = @JoinColumn(name = "parente_id"),
               inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Aluno> alunosResponsaveis;

}
