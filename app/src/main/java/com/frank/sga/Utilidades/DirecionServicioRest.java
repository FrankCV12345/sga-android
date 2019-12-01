package com.frank.sga.Utilidades;

public class DirecionServicioRest {
    public static String IP_SERVICIO_REST = "http://192.168.1.39:8071";
    public static String PATH_LOGIN ="/login";
    public static String PATH_USUARIO ="/usuario";
    public static String PATH_TIPOS_DOC ="/TipoDNI";
    public static String PATH_TIPOS_SEXO ="/TipoSexo";
    public static  String PATH_TIPOS_SOLICITUD = "/TipoSolicitud";
    public static  String PATH_SOLICITUDALUMNO = "/SolicitudesAlumno";
    public static String PATH_PROFESORES_GRUPO = "/grupo";
    public static  String PATH_CALIFICACION_PROFESORES = "/calificacionProfesor";
    public  static  String PATH_CURSOS_EN_GRUPO = "/CrusosCarreras";
    public static  String PATH_SOLICITUDES_POR_ALUMNO(Long idAlumno){
       if(idAlumno !=null && idAlumno > 0){
           return "/Alumno/"+idAlumno+"/Solicitudes";
       } else{
           return null;
       }
    }
}
