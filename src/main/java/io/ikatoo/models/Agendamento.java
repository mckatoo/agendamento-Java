/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ikatoo.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author mckatoo
 */
@Entity
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAgendamento;
    @ManyToOne
    @JoinColumn(name = "idProfessor")
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    private Turma turma;
    private Date dia;
    private Boolean preaula;
    private Boolean primeirop;
    private Boolean segundop;
    private Boolean datashow;
    private Boolean amplificador;
    private String observacao;

    public Integer getIdagendamento() {
        return idAgendamento;
    }

    public void setIdagendamento(Integer idagendamento) {
        this.idAgendamento = idagendamento;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Boolean getPreaula() {
        return preaula;
    }

    public void setPreaula(Boolean preaula) {
        this.preaula = preaula;
    }

    public Boolean getPrimeirop() {
        return primeirop;
    }

    public void setPrimeirop(Boolean primeirop) {
        this.primeirop = primeirop;
    }

    public Boolean getSegundop() {
        return segundop;
    }

    public void setSegundop(Boolean segundop) {
        this.segundop = segundop;
    }

    public Boolean getDatashow() {
        return datashow;
    }

    public void setDatashow(Boolean datashow) {
        this.datashow = datashow;
    }

    public Boolean getAmplificador() {
        return amplificador;
    }

    public void setAmplificador(Boolean amplificador) {
        this.amplificador = amplificador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
