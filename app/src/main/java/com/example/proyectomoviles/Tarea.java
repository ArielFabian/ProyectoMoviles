package com.example.proyectomoviles;

public class Tarea {
    public String clave;
    public String descripcion;
    public String fechaInicio;
    public String fechaEstimada;
    public String fechaTerminada;

    public Tarea(String clave, String descripcion, String fechaInicio, String fechaEstimada, String fechaTerminada) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaEstimada = fechaEstimada;
        this.fechaTerminada = fechaTerminada;
    }

    public Tarea() {
        this.clave = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.fechaEstimada = "";
        this.fechaTerminada = "";
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(String fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public String getFechaTerminada() {
        return fechaTerminada;
    }

    public void setFechaTerminada(String fechaTerminada) {
        this.fechaTerminada = fechaTerminada;
    }
}
