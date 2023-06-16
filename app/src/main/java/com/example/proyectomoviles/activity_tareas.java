package com.example.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_tareas extends AppCompatActivity {
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
        txtNombreTarea = (EditText) findViewById(R.id.txtnombretarea);
        txtFechaInicio = (EditText) findViewById(R.id.txtfechaestimada);
        txtFechaEstimada = (EditText) findViewById(R.id.txtfechaestimada);
        txtFechaFinal = (EditText) findViewById(R.id.txtfechafinal);
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
