package com.example.proyectomoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_perfil extends AppCompatActivity {
    public TextView txtNombre, txtCorreo;
    public Button cerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtNombre = (TextView) findViewById(R.id.tvNombre);
        txtCorreo = (TextView) findViewById(R.id.tvCorreo);
        cerrarSesion = (Button) findViewById(R.id.btnlogout);
    }
    public void cerrarSession(View view){

    }
}
