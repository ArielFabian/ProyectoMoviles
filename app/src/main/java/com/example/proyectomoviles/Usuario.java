package com.example.proyectomoviles;

import java.util.ArrayList;

public class Usuario {
    public String nombreCompleto;
    public String correo;
    public String contrasena;
    public boolean isProgramador;
    public boolean isPm;
    public ArrayList<Tarea> tareas;

    public Usuario(String nombreCompleto, String correo, String contrasena, boolean isProgramador, boolean isPm, ArrayList<Tarea> tareas) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.isProgramador = isProgramador;
        this.isPm = isPm;
        this.tareas = tareas;
    }

    public Usuario() {
        this.nombreCompleto = "";
        this.correo = "";
        this.contrasena = "";
        this.isProgramador = false;
        this.isPm = false;
        this.tareas = new ArrayList<Tarea>();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConstrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isProgramador() {
        return isProgramador;
    }

    public void setProgramador(boolean programador) {
        isProgramador = programador;
    }

    public boolean isPm() {
        return isPm;
    }

    public void setPm(boolean pm) {
        isPm = pm;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public String getContrasena() {
        return this.contrasena;
    }
}
