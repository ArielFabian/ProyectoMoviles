package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class lista_bugs extends AppCompatActivity {
    private ArrayAdapter<Bug> adapter;
    private List<Bug> bugList;
    private ListView listViewTasks;
    private String proyectos;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bugs);
        Intent intent = getIntent();
        listViewTasks = findViewById(R.id.listViewTasks);
        //Se recibe el objeto de registro
        this.usuario = intent.getParcelableExtra("nuevoUsuario");
        if(usuario.isPm){
            Log.d("Proyec","si");
            buscarProject(this.usuario.getCorreo());
        }else{
            Log.d("Proyec","no");
            buscarProgramador(this.usuario.getCorreo());
        }
        bugList=cargarTareas(this.proyectos);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bugList);

        listViewTasks.setAdapter(adapter);
    }
    private List<Bug> cargarTareas(String proyecto) {
        try {
            FileInputStream inputStream = openFileInput("bugs.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Bug>>() {}.getType();
            List<Bug> tareas = gson.fromJson(reader, listType);
            inputStream.close();

            List<Bug> tareasFiltradas = new ArrayList<>();
            if (tareas != null) {
                for (Bug tarea : tareas) {
                    if (tarea.enlaceFeature.equalsIgnoreCase(proyecto)) {
                        tareasFiltradas.add(tarea);
                    }
                }
            }
            return tareasFiltradas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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
                    return usuario; // Se encontró el usuario, retorna la información
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró el usuario, retorna null
    }
}