/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ikatoo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


/**
 *
 * @author mckatoo
 */

@Entity
public class Curso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCurso;
    @NotEmpty
    @Column(unique = true)
    private String curso;

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
