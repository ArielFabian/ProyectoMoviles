package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class activity_crear_tarea_future extends AppCompatActivity {
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
    private Button buttonLimpiar;
    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea_future);
        editTextClave = findViewById(R.id.editTextClave);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        editTextFechaInicio = findViewById(R.id.editTextFechaInicio);
        editTextFechaEstimada = findViewById(R.id.editTextFechaEstimada);
        editTextFechaTerminada = findViewById(R.id.editTextFechaTerminada);
        editTextProyecto = findViewById(R.id.editTextProyecto);
        editTextAsigno = findViewById(R.id.editTextAsigno);
        editTextAsignado = findViewById(R.id.editTextAsignado);
        checkBoxTerminado = findViewById(R.id.checkBoxTerminado);
        checkBoxRevisado = findViewById(R.id.checkBoxRevisado);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);
        dataManager = new DataManager(this);

    }

    public void clearFields(View view) {
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
    public void crearFuture(View view){
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
            Feature feature = new Feature(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada, proyecto, asigno, asignado, terminado, revisado);
            dataManager.agregarTarea(feature);
            Toast.makeText(activity_crear_tarea_future.this, "Feature creado", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscarFuture(View view){
        String clave = editTextClave.getText().toString();
        if (!clave.isEmpty()) {
            Feature feature = dataManager.buscarFeature(clave);
            if (feature != null) {
                Toast.makeText(this, "Feature encontrado: " + feature.descripcion, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Feature no encontrado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Clave vacia", Toast.LENGTH_SHORT).show();
        }
    }
    public void actualizarFutures(View view){
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
            Feature feature = new Feature(clave, descripcion, fechaInicio, fechaEstimada, fechaTerminada, proyecto, asigno, asignado, terminado, revisado);
            dataManager.actualizarTarea(feature);
            Toast.makeText(activity_crear_tarea_future.this, "Feature creado", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarFutures(View view){
        String clave = editTextClave.getText().toString();
        if (!clave.isEmpty()) {
            dataManager.eliminarTarea(clave);
        Toast.makeText(this, "Feature eliminado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Clave vacia", Toast.LENGTH_SHORT).show();

        }
    }
}
