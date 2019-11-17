package com.frank.sga.data.model;

public class estadoSolicitud {

    private long id;
    private String nombresolicitud;
    private String descripcion;

    public estadoSolicitud(long id, String nombresolicitud, String descripcion) {
        this.id = id;
        this.nombresolicitud = nombresolicitud;
        this.descripcion = descripcion;
    }

    public estadoSolicitud(long id) {
        this.id = id;
    }

    public estadoSolicitud() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombresolicitud() {
        return nombresolicitud;
    }

    public void setNombresolicitud(String nombresolicitud) {
        this.nombresolicitud = nombresolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
