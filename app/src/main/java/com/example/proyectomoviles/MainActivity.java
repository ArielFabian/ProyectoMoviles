package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btnRegistrarse, btnIniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrarse = (Button) findViewById(R.id.btnregistrar);
        btnIniciarSesion = (Button) findViewById(R.id.btniniciarsesion);
    }

    public void iniciarRegistro(View view){
        Intent intent = new Intent(this, activity_crear_cuenta.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view){
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }
}