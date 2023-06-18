package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    public EditText txtCorreoLogin, txtContrasenaLogin;
    private boolean userFounded;
    private String FILENAME="usuarios.json";
    private Button btnLogin;
    private Usuario usuarioEncontrado;
    private ArrayList<Usuario> usuarios;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtCorreoLogin = findViewById(R.id.txtcorreologin);
        txtContrasenaLogin = findViewById(R.id.txtcontrasenalogin);
        btnLogin = findViewById(R.id.btnlogin);
        //cargarUsuarios();
    }


    private void guardarUsuarios() {
        try {
            OutputStream outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuarios, new OutputStreamWriter(outputStream));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Usuario> cargarUsuarios() {
        try {
            InputStream inputStream = openFileInput(FILENAME);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
            ArrayList<Usuario> usuarios = gson.fromJson(new InputStreamReader(inputStream), listType);
            inputStream.close();
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public void login(View view){
        String correo = txtCorreoLogin.getText().toString();
        String contrasena = txtContrasenaLogin.getText().toString();

        if (!correo.isEmpty() && !contrasena.isEmpty()) {
            usuarios = cargarUsuarios();
            boolean usuarioEncontrado = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                    usuarioEncontrado = true;
                    break;
                }
            }
            if (usuarioEncontrado) {
                // El inicio de sesión fue exitoso
                Toast.makeText(login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Aquí puedes realizar la lógica correspondiente al inicio de sesión exitoso
            } else {
                // No se encontró un usuario con las credenciales proporcionadas
                Toast.makeText(login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(login.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

}