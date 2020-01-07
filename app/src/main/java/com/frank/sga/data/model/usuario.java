package com.frank.sga.data.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class usuario implements Serializable {
    private  int id;
    private String correo;
    private String password;
    private String  nombre;
    private String  apellidos;
    private String direcion;
    private String dni;
    private String telefono;
    private String nombreColegio;
    //private Date fechaNacimiento;



    private tipoDoc tipoDoc;

    private sexo sexo;

    public usuario() {
    }

    public usuario(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }



    public com.frank.sga.data.model.tipoDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(com.frank.sga.data.model.tipoDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public com.frank.sga.data.model.sexo getSexo() {
        return sexo;
    }

    public void setSexo(com.frank.sga.data.model.sexo sexo) {
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
