package com.example.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class activity_crear_cuenta extends AppCompatActivity {
    public EditText txtNombreCc, txtApellidoCc, txtCorreoCc, txtContrasenaCc;
    public Button btnCrearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        txtNombreCc = (EditText) findViewById(R.id.txtnombrecc);
        txtApellidoCc = (EditText) findViewById(R.id.txtapellidocc);
        txtCorreoCc = (EditText) findViewById(R.id.txtcorreocc);
        txtContrasenaCc = (EditText) findViewById(R.id.txtcontraseñacc);
        btnCrearCuenta = (Button) findViewById(R.id.btncrearcuenta);
    }

    public void registrar(View view){
        String nombre = txtNombreCc.getText().toString();
        String apellido = txtApellidoCc.getText().toString();
        String correo = txtCorreoCc.getText().toString();
        String contrasena = txtContrasenaCc.getText().toString();

        if(!nombre.equals("") && !apellido.equals("") && !correo.equals("") && !contrasena.equals("")){
            Usuario nuevoUsusario = new Usuario(
                    nombre + apellido,
                    correo,
                    contrasena,
                    false, // Agregar radios para verificar si es programador o project manager
                    false,
                    new ArrayList<Tarea>()
            );

            //Se inicia la app y se envia el objeto
            Intent intent = new Intent(this, activity_tareas.class);
            intent.putExtra("nuevoUsuario", (Serializable) nuevoUsusario);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se puede dejar ningun campo vacio, favor de verificar", Toast.LENGTH_LONG).show();
        }
    }
}
