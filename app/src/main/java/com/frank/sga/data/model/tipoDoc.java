package com.frank.sga.data.model;

public class tipoDoc {
    private long id;
    private String nombreDoc;
    private String descripcion;

    public tipoDoc(long id) {
        this.id = id;
    }

    public tipoDoc(long id, String nombreDoc, String descripcion) {
        this.id = id;
        this.nombreDoc = nombreDoc;
        this.descripcion = descripcion;
    }

    public tipoDoc() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
