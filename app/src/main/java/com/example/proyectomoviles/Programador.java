package com.example.proyectomoviles;

import java.util.ArrayList;

public class Programador  extends Usuario{
    public String area;
    public String registro;
    public String protectoAsignado;
    public String pmAsignado;
    public String lenguajeAsignado;

    public Programador(String nombreCompleto, String correo, String contrasena, boolean isProgramador, boolean isPm, ArrayList<Tarea> tareas, String area, String registro, String protectoAsignado, String pmAsignado, String lenguajeAsignado) {
        super(nombreCompleto, correo, contrasena, isProgramador, isPm, tareas);
        this.area = area;
        this.registro = registro;
        this.protectoAsignado = protectoAsignado;
        this.pmAsignado = pmAsignado;
        this.lenguajeAsignado = lenguajeAsignado;
    }

    public Programador(String area, String registro, String protectoAsignado, String pmAsignado, String lenguajeAsignado) {
        this.area = area;
        this.registro = registro;
        this.protectoAsignado = protectoAsignado;
        this.pmAsignado = pmAsignado;
        this.lenguajeAsignado = lenguajeAsignado;
    }

    public Programador(){
        this.nombreCompleto = "";
        this.correo = "";
        this.contrasena = "";
        this.isProgramador = false;
        this.isPm = false;
        this.area = "";
        this.registro = "";
        this.protectoAsignado = "";
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

    public String getProtectoAsignado() {
        return protectoAsignado;
    }

    public void setProtectoAsignado(String protectoAsignado) {
        this.protectoAsignado = protectoAsignado;
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
