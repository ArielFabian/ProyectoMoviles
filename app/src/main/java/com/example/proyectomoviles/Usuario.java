package com.example.proyectomoviles;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Usuario implements Parcelable {
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
    public  Usuario(String nombreCompleto, String correo){
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = null;
        this.isProgramador = false;
        this.isPm = false;
        this.tareas = null;
    }

    public Usuario() {
        this.nombreCompleto = "";
        this.correo = "";
        this.contrasena = "";
        this.isProgramador = false;
        this.isPm = false;
        this.tareas = new ArrayList<Tarea>();
    }

    protected Usuario(Parcel in) {
        nombreCompleto = in.readString();
        correo = in.readString();
        contrasena = in.readString();
        isProgramador = in.readByte() != 0;
        isPm = in.readByte() != 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombreCompleto);
        dest.writeString(correo);
        dest.writeString(contrasena);
        dest.writeByte((byte) (isProgramador ? 1 : 0));
        dest.writeByte((byte) (isPm ? 1 : 0));
    }
}
