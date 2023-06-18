package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class activity_perfil extends AppCompatActivity {

    public TextView txtNombre, txtCorreo;
    public Button cerrarSesion;
    public EditText pm,area,lenguaje,registro,proyecto;
    private boolean dl;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        txtNombre = (TextView) findViewById(R.id.tvNombre);
        txtCorreo = (TextView) findViewById(R.id.tvCorreo);
        cerrarSesion = (Button) findViewById(R.id.btnLogout);
        pm = (EditText) findViewById(R.id.etPMAsignado);
        area = (EditText) findViewById(R.id.etArea);
        lenguaje = (EditText) findViewById(R.id.etLenguajeAsignado);
        registro = (EditText) findViewById(R.id.etRegistro);
        proyecto = (EditText) findViewById(R.id.etProyectoAsignado);
        dl=false;
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        this.usuario = intent.getParcelableExtra("nuevoUsuario");

        if(usuario.isPm){
            pm.setText(usuario.nombreCompleto);
            pm.setEnabled(false);
            dl=true;
        }
        txtCorreo.setText(usuario.getCorreo());
        txtNombre.setText(usuario.getNombreCompleto());
        Programador pg= buscarProgramador(usuario.getCorreo());
        ProjectManager pj=buscarProject(usuario.getCorreo());
        if(pg!=null){
            this.area.setText(pg.getArea());
            this.registro.setText(pg.getRegistro());
            this.proyecto.setText(pg.getProtectoAsignado());
            this.pm.setText(pg.getPmAsignado());
            this.lenguaje.setText(pg.getLenguajeAsignado());
        }

    }
    public void cerrarSession(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void programador(){
        String nombreCompleto = txtNombre.getText().toString();
        String correo = txtCorreo.getText().toString();
        boolean isProgramador = true;
        boolean isPm = false;
        ArrayList<Tarea> tareas = null;
        String area = this.area.getText().toString();
        String registro = this.registro.getText().toString();
        String proyectoAsignado = this.proyecto.getText().toString();
        String pmAsignado = this.pm.getText().toString();
        String lenguajeAsignado = this.lenguaje.getText().toString();

        Programador programador = new Programador(nombreCompleto, correo, "", isProgramador, isPm, tareas,
                area, registro, proyectoAsignado, pmAsignado, lenguajeAsignado);

        guardarUsuarioEnJSON(programador);
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
                    this.area.setText(usuario.getArea());
                    this.registro.setText(usuario.getRegistro());
                    this.proyecto.setText(usuario.getProtectoAsignado());
                    this.pm.setText(usuario.getPmAsignado());
                    this.lenguaje.setText(usuario.getLenguajeAsignado());
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
                    this.area.setText(usuario.getArea());
                    this.registro.setText(usuario.getRegistro());
                    this.proyecto.setText(usuario.getProyectoAsignado());
                    this.pm.setText(usuario.getPmAsignado());
                    this.lenguaje.setText(usuario.getLenguajeAsignado());
                    return usuario; // Se encontró el usuario, retorna la información
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró el usuario, retorna null
    }
    public void projectManager(){
        String nombreCompleto = txtNombre.getText().toString();
        String correo = txtCorreo.getText().toString();
        boolean isProgramador = false;
        boolean isPm = true;
        ArrayList<Tarea> tareas = null;
        String area = this.area.getText().toString();
        String registro = this.registro.getText().toString();
        String proyectoAsignado = this.proyecto.getText().toString();
        String pmAsignado = this.pm.getText().toString();
        String lenguajeAsignado = this.lenguaje.getText().toString();

        ProjectManager projectManager = new ProjectManager(nombreCompleto, correo, "", isProgramador, isPm, tareas,
                area, registro, proyectoAsignado, pmAsignado, lenguajeAsignado);

        guardarUsuarioEnJSONProject(projectManager);
    }
    public void guardarUsuarioEnJSONProject(ProjectManager usuario) {
        // Verificar si el archivo ya existe
        File file = new File(getFilesDir(), "ProjectManager.json");
        boolean fileExists = file.exists();

        // Verificar si el archivo está vacío
        if (fileExists && file.length() == 0) {
            file.delete(); // Eliminar el archivo vacío
            fileExists = false;
        }

        // Obtener la lista de usuarios existente o crear una nueva lista
        List<ProjectManager> usuarios;
        if (fileExists) {
            Type tipoListaUsuarios = new TypeToken<List<ProjectManager>>() {}.getType();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                usuarios = new Gson().fromJson(reader, tipoListaUsuarios);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                usuarios = new ArrayList<>();
            }
        } else {
            usuarios = new ArrayList<>();
        }
        int usuarioIndex = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            ProjectManager usuarioExistente = usuarios.get(i);
            if (usuarioExistente.getCorreo().equals(usuario.getCorreo())) {
                usuarioIndex = i;
                break;
            }
        }

// Actualizar o agregar el usuario a la lista
        if (usuarioIndex != -1) {
            // Si el usuario ya existe, actualizar la información
            usuarios.set(usuarioIndex, usuario);
        } else {
            // Si el usuario no existe, agregarlo a la lista
            usuarios.add(usuario);
        }

// Convertir la lista de usuarios a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String usuariosJson = gson.toJson(usuarios);

// Guardar el JSON en el archivo
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(usuariosJson);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(View view){
        if(this.dl){
            this.projectManager();
            Toast.makeText(this,"Perfil editado PM",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity_perfil.this, activity_perfil.class);
            intent.putExtra("nuevoUsuario",this.usuario);
            startActivity(intent);
        }else{
            this.programador();
            Toast.makeText(this,"Perfil editado",Toast.LENGTH_LONG).show();
        }
        Intent intent= new Intent(this, menu_tareas.class);
        intent.putExtra("nuevoUsuario",usuario);
        startActivity(intent);
    }
    public void guardarUsuarioEnJSON(Programador usuario) {
        // Verificar si el archivo ya existe
        File file = new File(getFilesDir(), "programadores.json");
        boolean fileExists = file.exists();

        // Verificar si el archivo está vacío
        if (fileExists && file.length() == 0) {
            file.delete(); // Eliminar el archivo vacío
            fileExists = false;
        }

        // Obtener la lista de usuarios existente o crear una nueva lista
        List<Programador> usuarios;
        if (fileExists) {
            Type tipoListaUsuarios = new TypeToken<List<Programador>>() {}.getType();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                usuarios = new Gson().fromJson(reader, tipoListaUsuarios);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                usuarios = new ArrayList<>();
            }
        } else {
            usuarios = new ArrayList<>();
        }
        int usuarioIndex = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            Programador usuarioExistente = usuarios.get(i);
            if (usuarioExistente.getCorreo().equals(usuario.getCorreo())) {
                usuarioIndex = i;
                break;
            }
        }

// Actualizar o agregar el usuario a la lista
        if (usuarioIndex != -1) {
            // Si el usuario ya existe, actualizar la información
            usuarios.set(usuarioIndex, usuario);
        } else {
            // Si el usuario no existe, agregarlo a la lista
            usuarios.add(usuario);
        }

// Convertir la lista de usuarios a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String usuariosJson = gson.toJson(usuarios);

// Guardar el JSON en el archivo
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(usuariosJson);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}