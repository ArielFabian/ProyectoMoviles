package com.example.proyectomoviles;
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
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class crear_cuenta extends AppCompatActivity {
    public EditText txtNombreCc, txtApellidoCc, txtCorreoCc, txtContrasenaCc;
    public Button btnCrearCuenta;
    private RadioButton pm, pro;

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
        abrirArchivo();
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
        ArrayList<Usuario> usuarios = obtenerUsuarios();
        usuarios.add(usuario);
        try {
            FileOutputStream fileOutputStream = openFileOutput("usuarios.json", Context.MODE_PRIVATE);
            JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            jsonWriter.setIndent("  ");
            jsonWriter.beginArray();
            for (Usuario u : usuarios) {
                jsonWriter.beginObject();
                jsonWriter.name("nombreCompleto").value(u.getNombreCompleto());
                jsonWriter.name("correo").value(u.getCorreo());
                jsonWriter.name("contrasena").value(u.getContrasena());
                jsonWriter.name("programador").value(u.isProgramador());
                jsonWriter.name("pm").value(u.isPm());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "algo salio mal", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            FileInputStream fileInputStream = openFileInput("usuarios.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            String json = stringBuilder.toString();
            Gson gson = new Gson();
            usuarios = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private void abrirArchivo() {
        try {
            File file = new File(getFilesDir(), "usuarios.json");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}