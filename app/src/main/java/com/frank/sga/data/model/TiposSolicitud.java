package com.frank.sga.data.model;

public class TiposSolicitud {

    private long id;
    private String nombresolicitud;
    private String descripcion;
    private boolean softdelete;

    public TiposSolicitud() {
    }

    public TiposSolicitud(long id, String nombresolicitud, String descripcion) {
        this.id = id;
        this.nombresolicitud = nombresolicitud;
        this.descripcion = descripcion;
    }

    public TiposSolicitud(long id) {
        this.id = id;
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

    public boolean isSoftdelete() {
        return softdelete;
    }

    public void setSoftdelete(boolean softdelete) {
        this.softdelete = softdelete;
    }
}
