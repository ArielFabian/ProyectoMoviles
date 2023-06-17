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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    public EditText txtCorreoLogin, txtContrasenaLogin;
    private boolean userFounded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtCorreoLogin = (EditText) findViewById(R.id.txtcorreologin);
        txtContrasenaLogin = (EditText) findViewById(R.id.txtcontrasenalogin);
    }

    public void verificarDatos(View view) {
        String correo = txtCorreoLogin.getText().toString();
        String contrasena = txtContrasenaLogin.getText().toString();

        searchUserOnFile(correo,contrasena);
        Log.d("login",correo);
        Usuario user = new Usuario(correo.trim(), contrasena.trim());

        if (userFounded) {
            Intent intent = new Intent(login.this, android.R.menu.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        finish();
    }
    private void searchUserOnFile(String user, String pass){
        Pattern userPattern = Pattern.compile(user);
        Pattern passPattern = Pattern.compile(pass);

        int lineUser = 0;
        int linePass = 0;

        String []archivos = fileList();

        if (existeArchivo(archivos, "users.txt")){
            try {
                //Objeto que asocia al archivo especificado, para lectura
                InputStreamReader archivoInterno = new
                        InputStreamReader(openFileInput("users.txt"));
                //Objeto que relaciona el arhivo con el flujo de entrada (lectura)
                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
                String linea = leerArchivo.readLine();

                String textoLeido = "";
                Matcher matcherUser;
                Matcher matcherPass;

                while(linea != null && !userFounded){
                    matcherUser = userPattern.matcher(linea);

                    boolean userFound = matcherUser.find();
                    boolean passFound = linea.matches("(.*)"+pass);
                    if (userFound && passFound){
                        if (linePass == lineUser ){
                            userFounded = true;
                        }else{
                            Log.d("login",lineUser+"");
                            Log.d("login",""+linePass);

                        }
                    }
                    textoLeido += linea + '\n';
                    linea = leerArchivo.readLine();
                    if (!userFound){
                        lineUser++;
                    }

                    linePass++;

                }

                leerArchivo.close();
                archivoInterno.close();

            }catch (IOException e){
                Toast.makeText(this,"Error al leer el archivo.",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
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
}