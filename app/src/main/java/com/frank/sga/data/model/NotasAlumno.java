package com.frank.sga.data.model;

import java.sql.Date;

public class NotasAlumno {
    private long id;
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double examenfinal;
    private String estadoaprobado;
    private Date fechaaultimaActualizacion;

    private usuario alumno;
    private Grupo grupo;
    private cursosCarreras curso;

    public NotasAlumno() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public Double getExamenfinal() {
        return examenfinal;
    }

    public void setExamenfinal(Double examenfinal) {
        this.examenfinal = examenfinal;
    }

    public String getEstadoaprobado() {
        return estadoaprobado;
    }

    public void setEstadoaprobado(String estadoaprobado) {
        this.estadoaprobado = estadoaprobado;
    }

    public Date getFechaaultimaActualizacion() {
        return fechaaultimaActualizacion;
    }

    public void setFechaaultimaActualizacion(Date fechaaultimaActualizacion) {
        this.fechaaultimaActualizacion = fechaaultimaActualizacion;
    }

    public usuario getAlumno() {
        return alumno;
    }

    public void setAlumno(usuario alumno) {
        this.alumno = alumno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public cursosCarreras getCurso() {
        return curso;
    }

    public void setCurso(cursosCarreras curso) {
        this.curso = curso;
    }


    public String CalculaPromedio(){
        if (this.nota1 != null && this.nota2 !=null && this.nota3 !=null){
            return   String.valueOf((nota1+ nota2 +  nota3)/3);
        }else{
            return "--";
        }
    }
}
