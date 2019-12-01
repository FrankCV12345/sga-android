package com.frank.sga.data.model;

public class cursosCarreras {

    private long id;
    private Curso curso;
    private usuario profesor;
    private Grupo grupo;
    private Carrera carrera;

    public cursosCarreras() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public usuario getProfesor() {
        return profesor;
    }

    public void setProfesor(usuario profesor) {
        this.profesor = profesor;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
