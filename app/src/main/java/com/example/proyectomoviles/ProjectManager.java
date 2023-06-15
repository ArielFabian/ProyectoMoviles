package com.example.proyectomoviles;

import java.util.ArrayList;

public class ProjectManager extends Usuario{
    public String area;
    public String registro;
    public String proyectoAsignado;
    public String pmAsignado;
    public String lenguajeAsignado;

    public ProjectManager(String nombreCompleto, String correo, String contrasena, boolean isProgramador, boolean isPm, ArrayList<Tarea> tareas, String area, String registro, String proyectoAsignado, String pmAsignado, String lenguajeAsignado) {
        super(nombreCompleto, correo, contrasena, isProgramador, isPm, tareas);
        this.area = area;
        this.registro = registro;
        this.proyectoAsignado = proyectoAsignado;
        this.pmAsignado = pmAsignado;
        this.lenguajeAsignado = lenguajeAsignado;
    }

    public ProjectManager(String area, String registro, String proyectoAsignado, String pmAsignado, String lenguajeAsignado) {
        this.area = area;
        this.registro = registro;
        this.proyectoAsignado = proyectoAsignado;
        this.pmAsignado = pmAsignado;
        this.lenguajeAsignado = lenguajeAsignado;
    }

    public ProjectManager() {
        this.nombreCompleto = "";
        this.correo = "";
        this.contrasena = "";
        this.isProgramador = false;
        this.isPm = false;
        this.tareas = new ArrayList<Tarea>();
        this.area = "";
        this.registro = "";
        this.proyectoAsignado = "";
        this.pmAsignado = "";
        this.lenguajeAsignado = "";
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getProyectoAsignado() {
        return proyectoAsignado;
    }

    public void setProyectoAsignado(String proyectoAsignado) {
        this.proyectoAsignado = proyectoAsignado;
    }

    public String getPmAsignado() {
        return pmAsignado;
    }

    public void setPmAsignado(String pmAsignado) {
        this.pmAsignado = pmAsignado;
    }

    public String getLenguajeAsignado() {
        return lenguajeAsignado;
    }

    public void setLenguajeAsignado(String lenguajeAsignado) {
        this.lenguajeAsignado = lenguajeAsignado;
    }
}
