package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class crear_bugs extends AppCompatActivity {
    private EditText editTextClave;
    private EditText editTextDescripcion;
    private EditText editTextFechaInicio;
    private EditText editTextFechaEstimada;
    private EditText editTextFechaTerminada;
    private EditText editTextProyecto;
    private EditText editTextAsigno;
    private EditText editTextAsignado;
    private CheckBox checkBoxTerminado;
    private CheckBox checkBoxRevisado;
    private BugManager bugManager;
    private String proyectos;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_bugs);
        editTextClave = findViewById(R.id.editTextClave);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        editTextFechaInicio = findViewById(R.id.fechaInicio);
        editTextFechaEstimada = findViewById(R.id.editTextFechaEstimada);
        editTextFechaTerminada = findViewById(R.id.editTextFechaTerminada);
        editTextProyecto = findViewById(R.id.editTextProyecto);
        editTextAsigno = findViewById(R.id.editTextAsigno);
        editTextAsignado = findViewById(R.id.editTextAsignado);
        checkBoxTerminado = findViewById(R.id.checkBoxTerminado);
        checkBoxRevisado = findViewById(R.id.checkBoxRevisado);
        this.bugManager=new BugManager(this);
        Intent intent = getIntent();
        this.usuario = intent.getParcelableExtra("nuevoUsuario");
        if(usuario.isPm){
            Log.d("Proyec","si");
            buscarProject(this.usuario.getCorreo());
        }else{
            Log.d("Proyec","no");
            buscarProgramador(this.usuario.getCorreo());
        }
    }
    public void guardarBug(View view) {
        String clave = editTextClave.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String fechaInicio = editTextFechaInicio.getText().toString();
        String fechaEstimada = editTextFechaEstimada.getText().toString();
        String fechaTerminada = editTextFechaTerminada.getText().toString();
        String proyecto = editTextProyecto.getText().toString();
        String asigno = editTextAsigno.getText().toString();
        String asignado = editTextAsignado.getText().toString();
        boolean terminado = checkBoxTerminado.isChecked();
        boolean revisado = checkBoxRevisado.isChecked();


        if (clave.isEmpty() || descripcion.isEmpty() || fechaInicio.isEmpty() || fechaEstimada.isEmpty() || fechaTerminada.isEmpty() || proyecto.isEmpty() || asigno.isEmpty() || asignado.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
        } else {
            Bug bug = new Bug(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada, proyecto, asigno, asignado, false, false);
            bugManager.agregarTarea(bug);
            Toast.makeText(this, "Bug reportado", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarBug(View view){
        String clave = editTextClave.getText().toString();
        if (!clave.isEmpty()) {
            bugManager.eliminarTarea(clave);
            Toast.makeText(this, "Feature eliminado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Clave vacia", Toast.LENGTH_SHORT).show();

        }
    }
    public void actualizarBug(View view){
        String clave = editTextClave.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String fechaInicio = editTextFechaInicio.getText().toString();
        String fechaEstimada = editTextFechaEstimada.getText().toString();
        String fechaTerminada = editTextFechaTerminada.getText().toString();
        String proyecto = editTextProyecto.getText().toString();
        String asigno = editTextAsigno.getText().toString();
        String asignado = editTextAsignado.getText().toString();
        boolean terminado = checkBoxTerminado.isChecked();
        boolean revisado = checkBoxRevisado.isChecked();
        if (clave.isEmpty() || descripcion.isEmpty() || fechaInicio.isEmpty() || fechaEstimada.isEmpty() || fechaTerminada.isEmpty() || proyecto.isEmpty() || asigno.isEmpty() || asignado.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
        } else {
            Bug bug = new Bug(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada, proyecto, asigno, asignado, terminado, revisado);
            bugManager.actualizarTarea(bug);
            Toast.makeText(crear_bugs.this, "Bug "+clave+" editado Correctamente", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscarBug(View view){
        String clave = editTextClave.getText().toString();
        if (!clave.isEmpty()) {
            Bug bug = bugManager.buscarFeature(clave);
            if (bug != null) {
                //mostrar los valores en la vista
                editTextDescripcion.setText(bug.descripcion);
                editTextFechaInicio.setText(bug.fechaInicio);
                editTextFechaEstimada.setText(bug.fechaEstimada);
                editTextFechaTerminada.setText(bug.fechaTerminada);
                editTextProyecto.setText(bug.enlaceFeature);
                editTextAsigno.setText(bug.asigno);
                editTextAsignado.setText(bug.asignado);
                checkBoxTerminado.setChecked(bug.terminado);
                checkBoxRevisado.setChecked(bug.revisado);
                Toast.makeText(this, "Bug encontrado: " + bug.descripcion, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bug no encontrado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Clave vacia", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiar(View view){
        editTextClave.setText("");
        editTextDescripcion.setText("");
        editTextFechaInicio.setText("");
        editTextFechaEstimada.setText("");
        editTextFechaTerminada.setText("");
        editTextProyecto.setText("");
        editTextAsigno.setText("");
        editTextAsignado.setText("");
        checkBoxTerminado.setChecked(false);
        checkBoxRevisado.setChecked(false);
    }
    public Programador buscarProgramador(String correo) {
        try {
            File file = new File(getFilesDir(), "programadores.json");
            if (!file.exists()) {
                return null; // El archivo no existe, retorna null
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            Type tipoListaUsuarios = new TypeToken<List<Programador>>() {}.getType();
            List<Programador> usuarios = new Gson().fromJson(reader, tipoListaUsuarios);
            reader.close();

            for (Programador usuario : usuarios) {
                if (usuario.getCorreo().equals(correo)) {

                    this.proyectos=usuario.getProtectoAsignado();
                    editTextProyecto.setText(usuario.getProtectoAsignado());
                    return usuario; // Se encontró el usuario, retorna la información
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró el usuario, retorna null
    }
    public ProjectManager buscarProject(String correo) {
        try {
            File file = new File(getFilesDir(), "ProjectManager.json");
            if (!file.exists()) {
                return null; // El archivo no existe, retorna null
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            Type tipoListaUsuarios = new TypeToken<List<ProjectManager>>() {}.getType();
            List<ProjectManager> usuarios = new Gson().fromJson(reader, tipoListaUsuarios);
            reader.close();

            for (ProjectManager usuario : usuarios) {
                if (usuario.getCorreo().equals(correo)) {
                    this.proyectos=usuario.getProyectoAsignado();
                    editTextProyecto.setText(usuario.getProyectoAsignado());
                    return usuario; // Se encontró el usuario, retorna la información
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró el usuario, retorna null
    }
}