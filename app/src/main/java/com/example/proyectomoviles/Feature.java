package com.example.proyectomoviles;

import com.google.gson.Gson;

public class Feature extends Tarea{
    public String proyecto;
    public String asigno;
    public String asignado;
    public boolean terminado;
    public boolean revisado;

    public Feature(String clave, String descripcion, String fechaInicio, String fechaEstimada, String fechaTerminada, String proyecto, String asigno, String asignado, boolean terminado, boolean revisado) {
        super(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada);
        this.proyecto = proyecto;
        this.asigno = asigno;
        this.asignado = asignado;
        this.terminado = terminado;
        this.revisado = revisado;
    }

    public Feature(String proyecto, String asigno, String asignado, boolean terminado, boolean revisado) {
        this.proyecto = proyecto;
        this.asigno = asigno;
        this.asignado = asignado;
        this.terminado = terminado;
        this.revisado = revisado;
    }

    public Feature() {
        this.clave = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.fechaEstimada = "";
        this.fechaTerminada = "";
        this.proyecto = "";
        this.asigno = "";
        this.asignado = "";
        this.terminado = false;
        this.revisado = false;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
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

    public String getClave(){
        return this.clave;
    }
    @Override
    public String toString() {
        String terminadoString = terminado ? "Sí" : "No";
        String asignadoString = revisado ? "Sí" : "No";

        return "Tarea= " + clave + '\n' +
                "Descripcion= " + descripcion + '\n' +
                "Fecha Inicio= " + fechaInicio + '\n' +
                "Fecha Estimada= " + fechaEstimada + '\n' +
                "Fecha Terminada= " + fechaTerminada + '\n' +
                "Proyecto= "+ proyecto + '\n' +
                "Asigno= " + asigno + '\n' +
                "Asignado= " + asignado + '\n' +
                "Terminado= " + terminadoString +'\n'+
                "Revisado= " + asignadoString;
    }
}
