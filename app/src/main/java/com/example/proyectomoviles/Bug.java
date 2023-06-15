package com.example.proyectomoviles;

public class Bug extends Tarea{
    public String enlaceFeature;
    public String asigno;
    public String asignado;
    public boolean terminado;
    public boolean revisado;

    public Bug(String clave, String descripcion, String fechaInicio, String fechaEstimada, String fechaTerminada, String proyecto, String asigno, String asignado, boolean terminado, boolean revisado) {
        super(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada);
        this.enlaceFeature = proyecto;
        this.asigno = asigno;
        this.asignado = asignado;
        this.terminado = terminado;
        this.revisado = revisado;
    }

    public Bug(String proyecto, String asigno, String asignado, boolean terminado, boolean revisado) {
        this.enlaceFeature = proyecto;
        this.asigno = asigno;
        this.asignado = asignado;
        this.terminado = terminado;
        this.revisado = revisado;
    }

    public Bug() {
        this.clave = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.fechaEstimada = "";
        this.fechaTerminada = "";
        this.enlaceFeature = "";
        this.asigno = "";
        this.asignado = "";
        this.terminado = false;
        this.revisado = false;
    }

    public String getEnlaceFeature() {
        return enlaceFeature;
    }

    public void setEnlaceFeature(String proyecto) {
        this.enlaceFeature = proyecto;
    }

    public String getAsigno() {
        return asigno;
    }

    public void setAsigno(String asigno) {
        this.asigno = asigno;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }
}
