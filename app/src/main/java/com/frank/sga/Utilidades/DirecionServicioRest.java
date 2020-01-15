package com.frank.sga.Utilidades;

public class DirecionServicioRest {
    public static String IP_SERVICIO_REST = "http://192.168.1.35:8071";
    public static String PATH_LOGIN ="/login";
    public static String PATH_USUARIO ="/usuario";
    public static String PATH_TIPOS_DOC ="/TipoDNI";
    public static String PATH_TIPOS_SEXO ="/TipoSexo";
    public static  String PATH_TIPOS_SOLICITUD = "/TipoSolicitud";
    public static  String PATH_SOLICITUDALUMNO = "/SolicitudesAlumno";
    public static String PATH_PROFESORES_GRUPO = "/grupo";
    public static  String PATH_CALIFICACION_PROFESORES = "/calificacionProfesor";
    public static  String PATH_BUSCARCALIFICACION_POR_PROFESOR_ALUMNO = "/calificacion";
    public  static  String PATH_CURSOS_EN_GRUPO = "/CrusosCarreras";
    public  static String PATH_NOTAS_CURSO = "/NotasAlumno";
    public  static String PATH_NOTAS_PORGRUPO = "/cursoCarrera";
    public static  String PATH_SOLICITUDES_POR_ALUMNO(Long idAlumno){
       if(idAlumno !=null && idAlumno > 0){
           return "/Alumno/"+idAlumno+"/Solicitudes";
       } else{
           return null;
       }
    }
    public static String PATH_NOTAS_POR_ALUMNO(long idALumno,long idCursoEnGrupo){
        return PATH_NOTAS_CURSO+"/"+idALumno+PATH_NOTAS_PORGRUPO+"/"+idCursoEnGrupo;
    }



}
