package com.example.proyectomoviles;
import static android.provider.Telephony.Mms.Part.FILENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class crear_cuenta extends AppCompatActivity {
    public EditText txtNombreCc, txtApellidoCc, txtCorreoCc, txtContrasenaCc;
    public Button btnCrearCuenta;
    private RadioButton pm, pro;
    private String FILENAME="usuarios.json"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta);

        txtNombreCc = findViewById(R.id.txtnombrecc);
        txtApellidoCc = findViewById(R.id.txtapellidocc);
        txtCorreoCc = findViewById(R.id.txtcorreocc);
        txtContrasenaCc = findViewById(R.id.txtcontraseñacc);
        btnCrearCuenta = findViewById(R.id.btncrearcuenta);
        pm = findViewById(R.id.rbtnPM);
        pro = findViewById(R.id.rbtnProgramador);
    }

    public void registrar(View view) {
        String nombre = txtNombreCc.getText().toString();
        String apellido = txtApellidoCc.getText().toString();
        String correo = txtCorreoCc.getText().toString();
        String contrasena = txtContrasenaCc.getText().toString();
        boolean programador=false;
        boolean pm=false;

        if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
            if (this.pm.isChecked()) {
                pm=true;

            }else{
                programador=true;
            }
            Usuario nuevoUsuario = new Usuario(nombre+apellido, correo, contrasena,programador,pm,null);
            guardarUsuario(nuevoUsuario);
            Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, activity_tareas.class);
            // intent.putExtra("nuevoUsuario", (Serializable) nuevoUsuario);
            // startActivity(intent);
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarUsuario(Usuario usuario) {
        // Cargar los usuarios existentes desde el archivo JSON
        List<Usuario> usuarios = cargarUsuarios();

        // Agregar el nuevo usuario a la lista
        usuarios.add(usuario);

        // Crear una instancia de Gson para convertir la lista de usuarios a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsuarios = gson.toJson(usuarios);

        try {
            // Abrir el archivo en modo privado para escritura
            FileOutputStream outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            // Escribir el JSON de la lista de usuarios en el archivo
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(jsonUsuarios);
            writer.close();

            Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
    private List<Usuario> cargarUsuarios() {
        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(bufferedReader, listType);

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


}