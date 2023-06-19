package com.example.proyectomoviles;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BugManager {
    private static final String FILENAME = "bugs.json";
    private List<Bug> tareas;
    private Context context;

    public BugManager(Context context) {
        this.context = context;
        tareas = cargarTareas();
    }

    public List<Bug> getTareas() {
        return tareas;
    }

    public void agregarTarea(Bug tarea) {
        tareas.add(tarea);
        guardarTareas();
    }

    public void actualizarTarea(Bug tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getClave().equals(tarea.getClave())) {
                tareas.set(i, tarea);
                guardarTareas();
                return;
            }
        }
    }

    public void eliminarTarea(String clave) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getClave().equals(clave)) {
                tareas.remove(i);
                guardarTareas();
                return;
            }
        }
    }

    private void guardarTareas() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(tareas);

            FileOutputStream outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Bug> cargarTareas() {
        try {
            FileInputStream inputStream = context.openFileInput(FILENAME);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Bug>>() {}.getType();
            List<Bug> tareas = gson.fromJson(reader, listType);
            inputStream.close();
            return tareas != null ? tareas : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Bug buscarFeature(String clave) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getClave().equals(clave)) {
                return tareas.get(i);
            }
        }
        return null;
    }
}
