/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ikatoo.models;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 *
 * @author mckatoo
 */

@Entity
@NamedQueries(
        @NamedQuery(name = "Turma.buscaPorCurso",
                query = "SELECT t from Turma t where t.curso.idCurso = :id")
)
public class Turma implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTurma;
    @ManyToOne
    @JoinColumn(name = "idCurso")
    @NotNull
    private Curso curso;
    @NotEmpty
    @Column(unique = true)
    private String turma;

    public Long getIdturma() {
        return idTurma;
    }

    public void setIdturma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

}
