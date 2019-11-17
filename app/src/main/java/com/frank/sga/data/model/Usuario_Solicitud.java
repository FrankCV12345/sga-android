package com.frank.sga.data.model;

import java.sql.Date;

public class Usuario_Solicitud {

    private Long  id;
    private String  comentarios;
    private Date fechaingresosolicitud;

    private usuario usuariosolicitud;
    private usuario usuarioresponsable;
    private TiposSolicitud tiposolicitud;
    private estadoSolicitud estadosolicitud;


    public Usuario_Solicitud(Long id, String comentarios, Date fechaingresosolicitud, usuario usuariosolicitud, usuario usuarioresponsable, TiposSolicitud tiposolicitud, estadoSolicitud estadosolicitud) {
        this.id = id;
        this.comentarios = comentarios;
        this.fechaingresosolicitud = fechaingresosolicitud;
        this.usuariosolicitud = usuariosolicitud;
        this.usuarioresponsable = usuarioresponsable;
        this.tiposolicitud = tiposolicitud;
        this.estadosolicitud = estadosolicitud;
    }

    public Usuario_Solicitud(String comentarios, usuario usuariosolicitud, TiposSolicitud tiposolicitud) {
        this.comentarios = comentarios;
        this.usuariosolicitud = usuariosolicitud;
        this.tiposolicitud = tiposolicitud;
    }

    public Usuario_Solicitud() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaingresosolicitud() {
        return fechaingresosolicitud;
    }

    public void setFechaingresosolicitud(Date fechaingresosolicitud) {
        this.fechaingresosolicitud = fechaingresosolicitud;
    }

    public usuario getUsuariosolicitud() {
        return usuariosolicitud;
    }

    public void setUsuariosolicitud(usuario usuariosolicitud) {
        this.usuariosolicitud = usuariosolicitud;
    }

    public usuario getUsuarioresponsable() {
        return usuarioresponsable;
    }

    public void setUsuarioresponsable(usuario usuarioresponsable) {
        this.usuarioresponsable = usuarioresponsable;
    }

    public TiposSolicitud getTiposolicitud() {
        return tiposolicitud;
    }

    public void setTiposolicitud(TiposSolicitud tiposolicitud) {
        this.tiposolicitud = tiposolicitud;
    }

    public estadoSolicitud getEstadosolicitud() {
        return estadosolicitud;
    }

    public void setEstadosolicitud(estadoSolicitud estadosolicitud) {
        this.estadosolicitud = estadosolicitud;
    }
}
