package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_test);
        DataManager dt = new DataManager(this);
        featureList = cargarTareas();
        recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        tareaAdapter = new FeatureAdapter(featureList);
        recyclerViewTareas.setAdapter(tareaAdapter);
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        this.usuario = intent.getParcelableExtra("nuevoUsuario");
    }
    private List<Feature> cargarTareas() {
        List<Feature> tareas = new ArrayList<>();

        try {
            InputStream inputStream = getAssets().open("future.json");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feature>>() {}.getType();
            tareas = gson.fromJson(new InputStreamReader(inputStream), listType);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tareas;
    }
}
