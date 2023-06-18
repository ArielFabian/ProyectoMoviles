package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class crear_tareas extends AppCompatActivity {

    public ArrayList<Usuario> usuariosRegistrados;
    public EditText txtNombreTarea, txtFechaInicio,txtFechaEstimada, txtFechaFinal;
    public Button btnCrearTarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tareas);
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        Usuario usuario = (Usuario) intent.getSerializableExtra("nuevoUsuario");
        txtNombreTarea = (EditText) findViewById(R.id.txtEditnombretarea);
        txtFechaInicio = (EditText) findViewById(R.id.txtEditfechainicio);
        txtFechaEstimada = (EditText) findViewById(R.id.txtEditfechaestimada);
        txtFechaFinal = (EditText) findViewById(R.id.txtEditfechafinal);
        btnCrearTarea = (Button) findViewById(R.id.btncreartarea);
    }
    public void crearTarea (View view){
        String nombreTarea = txtNombreTarea.getText().toString();
        String fechaInicio = txtFechaInicio.getText().toString();
        String fechaEstimada = txtFechaEstimada.getText().toString();
        String fechaFinal = txtFechaFinal.getText().toString();

        if(!nombreTarea.equals("")||!fechaInicio.equals("")||!fechaEstimada.equals("")||!fechaFinal.equals("")){

        }else{
            Toast.makeText(this,"Tiene campos en blanco",Toast.LENGTH_LONG).show();
        }
    }
}