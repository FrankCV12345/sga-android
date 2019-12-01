package com.frank.sga.data.model;

import java.io.Serializable;
@SuppressWarnings("serial")
public class sexo implements Serializable {
    private long id;
    private String nombreSexo;
    private String Descripcion;

    public sexo(long id, String nombreSexo, String descripcion) {
        this.id = id;
        this.nombreSexo = nombreSexo;
        Descripcion = descripcion;
    }

    public sexo(long id) {
        this.id = id;
    }

    public sexo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
