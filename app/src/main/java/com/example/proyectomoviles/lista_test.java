package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class lista_test extends AppCompatActivity {

    private List<Feature> featureList;
    private DataManager dt;
    private Usuario usuario;
    private RecyclerView recyclerViewTareas;
    private FeatureAdapter tareaAdapter;
    private ArrayAdapter<Feature> adapter;
    private ListView listViewTasks;
    private String proyectos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_test);
        DataManager dt = new DataManager(this);
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        this.usuario = intent.getParcelableExtra("nuevoUsuario");

        if(usuario.isPm){
            Log.d("Proyec","si");
            buscarProject(this.usuario.getCorreo());
        }else{
            Log.d("Proyec","no");
            buscarProgramador(this.usuario.getCorreo());
        }
        featureList = cargarTareas(this.proyectos);
        listViewTasks = findViewById(R.id.listViewTasks);
        //Log.d("Tareas",featureList.toString() );
        //recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
       // recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
       // tareaAdapter = new FeatureAdapter(featureList);
       // recyclerViewTareas.setAdapter(tareaAdapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, featureList);

        listViewTasks.setAdapter(adapter);

    }
    private List<Feature> cargarTareas(String proyecto) {
        try {
            FileInputStream inputStream = openFileInput("future.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feature>>() {}.getType();
            List<Feature> tareas = gson.fromJson(reader, listType);
            inputStream.close();

            List<Feature> tareasFiltradas = new ArrayList<>();
            if (tareas != null) {
                for (Feature tarea : tareas) {
                    if (tarea.proyecto.equalsIgnoreCase(proyecto)) {
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
