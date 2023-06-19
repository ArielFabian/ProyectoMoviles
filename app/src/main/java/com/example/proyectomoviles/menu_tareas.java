package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class menu_tareas extends AppCompatActivity {
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tareas);
        Intent intent = getIntent();
        //Se recibe el objeto de registro
        this.usuario = intent.getParcelableExtra("nuevoUsuario");
    }
    //Método para mostrar u ocultar el menu dentro del Activity
    public boolean onCreateOptionsMenu(Menu menu){
//Se asocia el xml al Activity
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
        return true;
    }//OnCreateOptionsMenu
    public boolean onOptionsItemSelected(MenuItem item){
//Variable para identificar la opción
        int id = item.getItemId();
        switch (id){
            case R.id.menuitemAñadir:
                Intent intent = new Intent(this, activity_crear_tarea_future.class);
                intent.putExtra("nuevoUsuario",usuario);
                startActivity(intent);
                break;
            case R.id.menuitemVertarea:

                Intent consultar = new Intent(this,lista_test.class);
                consultar.putExtra("nuevoUsuario",usuario);
                startActivity(consultar);

                break;
            case R.id.menuitemperfil:
                Intent intent1 = new Intent(this,activity_perfil.class);
                intent1.putExtra("nuevoUsuario",usuario);
                startActivity(intent1);
                break;

        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected
}