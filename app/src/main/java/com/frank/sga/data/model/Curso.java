package com.frank.sga.data.model;

public class Curso {

    private long id;
    private String nombrecurso;
    private String descripioncurso;
    private boolean sfotdelete;

    public Curso() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombrecurso() {
        return nombrecurso;
    }

    public void setNombrecurso(String nombrecurso) {
        this.nombrecurso = nombrecurso;
    }

    public String getDescripioncurso() {
        return descripioncurso;
    }

    public void setDescripioncurso(String descripioncurso) {
        this.descripioncurso = descripioncurso;
    }

    public boolean isSfotdelete() {
        return sfotdelete;
    }

    public void setSfotdelete(boolean sfotdelete) {
        this.sfotdelete = sfotdelete;
    }
}
