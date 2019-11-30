package com.frank.sga.data.model;

import java.sql.Date;

public class CalificacionAlumnoProfesor {

    private long id;
    private int dominatema;
    private int aclaradudas;
    private int expresaclaramente;
    private String comentario;
    private Date fechacalificacion;
    private usuario profesorcalificado;
    private usuario alumnocalifica;

    public CalificacionAlumnoProfesor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDominatema() {
        return dominatema;
    }

    public void setDominatema(int dominatema) {
        this.dominatema = dominatema;
    }

    public int getAclaradudas() {
        return aclaradudas;
    }

    public void setAclaradudas(int aclaradudas) {
        this.aclaradudas = aclaradudas;
    }

    public int getExpresaclaramente() {
        return expresaclaramente;
    }

    public void setExpresaclaramente(int expresaclaramente) {
        this.expresaclaramente = expresaclaramente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechacalificacion() {
        return fechacalificacion;
    }

    public void setFechacalificacion(Date fechacalificacion) {
        this.fechacalificacion = fechacalificacion;
    }

    public usuario getProfesorcalificado() {
        return profesorcalificado;
    }

    public void setProfesorcalificado(usuario profesorcalificado) {
        this.profesorcalificado = profesorcalificado;
    }

    public usuario getAlumnocalifica() {
        return alumnocalifica;
    }

    public void setAlumnocalifica(usuario alumnocalifica) {
        this.alumnocalifica = alumnocalifica;
    }
}
