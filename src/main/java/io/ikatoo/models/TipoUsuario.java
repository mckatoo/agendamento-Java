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
public class TipoUsuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTipoUsuario;
    @NotEmpty
    private String tipo;
    @NotEmpty
    @Column(unique = true)
    private Integer nivel;

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    

}
