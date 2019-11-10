package com.frank.sga.Utilidades;

public class MetodosUtilitarios {

    public static void CerrarSescion(){
        MemoriaLocal.setDefaults("token",null,MemoriaLocal.CONTEXTOLOGIN);

    }
}
