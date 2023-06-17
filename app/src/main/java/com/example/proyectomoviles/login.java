package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class login extends AppCompatActivity {

    public EditText txtCorreoLogin, txtContrasenaLogin;

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

        if (!correo.equals("") && !contrasena.equals("")) {
            if (validarCredenciales(correo, contrasena)) {
                // Iniciar sesión exitosa, puedes realizar las acciones necesarias
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            } else {
                // Credenciales incorrectas, muestra un mensaje de error
                Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ninguno de los campos puede quedar vacío, favor de verificar", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarCredenciales(String correo, String contrasena) {
        try {
            XmlResourceParser parser = getResources().getXml(R.xml.usuarios);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("usuario")) {
                    String correoXml = "";
                    String contrasenaXml = "";

                    while (!(parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("usuario"))) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) {
                            String tag = parser.getName();

                            if (tag.equals("correo")) {
                                parser.next();
                                correoXml = parser.getText();
                            } else if (tag.equals("contrasena")) {
                                parser.next();
                                contrasenaXml = parser.getText();
                            }
                        }

                        parser.next();
                    }

                    if (correoXml.equals(correo) && contrasenaXml.equals(contrasena)) {
                        return true; // El usuario existe y las credenciales son correctas
                    }
                }

                parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return false; // El usuario no existe o las credenciales son incorrectas
    }
}