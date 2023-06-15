package com.example.proyectomoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {
    public EditText txtCorreoLogin, txtContrasenaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreoLogin = (EditText) findViewById(R.id.txtcorreologin);
        txtContrasenaLogin = (EditText) findViewById(R.id.txtcontrasenalogin);
    }

    public void verificarDatos(View view){
        String correo = txtCorreoLogin.getText().toString();
        String contrasena = txtContrasenaLogin.getText().toString();

        if(!correo.equals("") && !contrasena.equals("")){
            //Aqui se tiene que extraer el texto del archivo y verificar que el usuario exista, si no, se manda un toast para avisar que el usuario no esta registrado
        } else {
            Toast.makeText(this, "Ninguno de los campos puede quedar vacio, favor de verificar", Toast.LENGTH_LONG).show();
        }
    }
}
