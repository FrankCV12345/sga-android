package com.frank.sga.data.model;

import java.util.ArrayList;
import java.util.List;

public class PerfilCalificacionProfesor {

    private int promedioAclaraDudas;
    private int primedioExpresaClaramente;
    private int dominaTema;

    private int cantidadCalificaciones;
    private String nombreProfesor;
    private String apellidosProfesor;
    private List<String> comentarios = new ArrayList<>() ;
    private List<CalificacionAlumnoProfesor> listacalificaciones = new ArrayList<>();

    public PerfilCalificacionProfesor() {
    }

    public int getPromedioAclaraDudas() {
        return promedioAclaraDudas;
    }

    public void setPromedioAclaraDudas(int promedioAclaraDudas) {
        this.promedioAclaraDudas = promedioAclaraDudas;
    }

    public int getPrimedioExpresaClaramente() {
        return primedioExpresaClaramente;
    }

    public void setPrimedioExpresaClaramente(int primedioExpresaClaramente) {
        this.primedioExpresaClaramente = primedioExpresaClaramente;
    }

    public int getDominaTema() {
        return dominaTema;
    }

    public void setDominaTema(int dominaTema) {
        this.dominaTema = dominaTema;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public List<CalificacionAlumnoProfesor> getListacalificaciones() {
        return listacalificaciones;
    }

    public void setListacalificaciones(List<CalificacionAlumnoProfesor> listacalificaciones) {
        this.listacalificaciones = listacalificaciones;
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidosProfesor() {
        return apellidosProfesor;
    }

    public void setApellidosProfesor(String apellidosProfesor) {
        this.apellidosProfesor = apellidosProfesor;
    }
}
