package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.util.ArrayList;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class crear_cuenta extends AppCompatActivity {
    public EditText txtNombreCc, txtApellidoCc, txtCorreoCc, txtContrasenaCc;
    public Button btnCrearCuenta;
    private String previousUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta);

        txtNombreCc = findViewById(R.id.txtnombrecc);
        txtApellidoCc = findViewById(R.id.txtapellidocc);
        txtCorreoCc = findViewById(R.id.txtcorreocc);
        txtContrasenaCc = findViewById(R.id.txtcontraseñacc);
        btnCrearCuenta = findViewById(R.id.btncrearcuenta);
        abrirArchivo();
    }

    public void registrar(View view) {
        String nombre = txtNombreCc.getText().toString();
        String apellido = txtApellidoCc.getText().toString();
        String correo = txtCorreoCc.getText().toString();
        String contrasena = txtContrasenaCc.getText().toString();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
            guardarArchivo();
            Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


    private void guardarArchivo(){
        try {
            //Objeto que asocia al archivo especificado, para escritura
            OutputStreamWriter archivoInterno = new OutputStreamWriter(
                    openFileOutput("users.txt", Activity.MODE_PRIVATE));
            archivoInterno.write(registerUserOnFile(previousUsers));
            archivoInterno.flush();
            archivoInterno.close();

        }catch (IOException e){
            Toast.makeText(this, "Error al escribir en el archivo", Toast.LENGTH_SHORT).show();
        }
    }
    private void abrirArchivo(){
        String []archivos = fileList();

        if (existeArchivo(archivos, "users.txt")){
            try {
                //Objeto que asocia al archivo especificado, para lectura
                InputStreamReader archivoInterno = new
                        InputStreamReader(openFileInput("users.txt"));
                //Objeto que relaciona el arhicov con el flujo de entrada (lectura)
                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
                String linea = leerArchivo.readLine();

                String textoLeido = "";

                while(linea != null){
                    textoLeido += linea + '\n';
                    linea = leerArchivo.readLine();
                }

                leerArchivo.close();
                archivoInterno.close();
                previousUsers = textoLeido;
            }catch (IOException e){
                Toast.makeText(this,"Error al leer el archivo.",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean existeArchivo(String []archivos, String s){
        for (String archivo : archivos) {
            if (s.equals(archivo)) {
                return true;
            }
        }
        return false;
    }
    private String registerUserOnFile(String currentContentOfTheFile){
        StringBuilder stringBuilder = new StringBuilder();
        if (currentContentOfTheFile != null){
            stringBuilder.append(currentContentOfTheFile);
            stringBuilder.append("\n");
            stringBuilder.append("Nombre Completo: ");
            stringBuilder.append( txtNombreCc.getText().toString());
            stringBuilder.append("Correo: ");
            stringBuilder.append(this.txtCorreoCc.getText().toString());
            stringBuilder.append("Contrasena: ");
            stringBuilder.append(this.txtContrasenaCc.getText().toString());


        }else{
            stringBuilder.append("\n");
            stringBuilder.append("Nombre Completo: ");
            stringBuilder.append( txtNombreCc.getText().toString());
            stringBuilder.append("Correo: ");
            stringBuilder.append(this.txtCorreoCc.getText().toString());
            stringBuilder.append("Contrasena: ");
            stringBuilder.append(this.txtContrasenaCc.getText().toString());
        }
        return stringBuilder.toString();
    }
}