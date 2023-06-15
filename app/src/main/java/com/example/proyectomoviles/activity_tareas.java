package com.example.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_tareas extends AppCompatActivity {
    public ArrayList<Usuario> usuariosRegistrados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        Usuario usuario = (Usuario) intent.getSerializableExtra("nuevoUsuario");
    }
}
