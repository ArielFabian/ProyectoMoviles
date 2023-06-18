package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    public EditText txtCorreoLogin, txtContrasenaLogin;
    private boolean userFounded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtCorreoLogin = findViewById(R.id.txtcorreologin);
        txtContrasenaLogin = findViewById(R.id.txtcontrasenalogin);
    }

    public void verificarDatos(View view) {
        String correo = txtCorreoLogin.getText().toString();
        String contrasena = txtContrasenaLogin.getText().toString();

        ArrayList<Usuario> usuarios = obtenerUsuarios();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                userFounded = true;
                break;
            }
        }

        if (userFounded) {
            Intent intent = new Intent(login.this, menu_tareas.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
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

            // Parsear el JSON utilizando la biblioteca Gson
            Gson gson = new Gson();
            usuarios = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}