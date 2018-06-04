/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ikatoo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


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
    private Integer idTurma;
    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;
    private String turma;

    public Integer getIdturma() {
        return idTurma;
    }

    public void setIdturma(Integer idTurma) {
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
