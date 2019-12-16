package com.example.jose.listadeenemigos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView menu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ImageView imagenEnemigo;

    final int GALLERY_REQUEST = 4;

    //FragmentManager
    FragmentManager manager;

    //Valor inicial dentor de fragment
    Fragment fragment = null;

    //ArrayList
    DatosJson datosEnemigos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Accede al manager
        manager = getSupportFragmentManager();

        imagenEnemigo = findViewById(R.id.imagen);
        menu = findViewById(R.id.menu);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeScreen(position);
            }
        });

        //Cargar los datos guardados al abrir la aplicacion
        datosEnemigos = cargar();

        //Crear un nuevo array en caso de que no haya datos
        if(datosEnemigos == null){
            datosEnemigos = new DatosJson();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrir_menu, R.string.cerrar_menu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeScreen(0);
    }

    /*
    * Con este metodo cambiaremos de fragments a traves del menu lateral
    * */
    void changeScreen(int screen) {
        Fragment fragment = null;
        FragmentManager manager = getSupportFragmentManager();

        switch (screen) {
            case 0:
                fragment = new TopEnemies();
                break;
            case 1:
                fragment = new AddEnemies();
                break;
            case 2:
                fragment = new PhotosEnemies();
                break;
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.START);
    }

    //Con esto obtenemos los datos de ArrayList
    public DatosJson recibirDatos(){

        return datosEnemigos;
    }

    //Con esto mandamos nuevos enemigos en AddEnemies y los almacenamos en el Array
    public void enviarEnemigo(Datos enemigo){
        datosEnemigos.add(enemigo);//datos del enemigo
    }

    //Guarda los datos en Json
    public void guardar(DatosJson enemigo){
        String json = enemigo.toJson();
        SharedPreferences preferences = getSharedPreferences("Enemigos", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("enemigo", json);
        editor.commit();
    }

    //Guarda los datos al cerrar la aplicación
    @Override
    protected void onPause() {
        super.onPause();
        guardar(datosEnemigos);
    }

    //Con esto accedemos a los datos guardados
    DatosJson cargar(){
        SharedPreferences preferences = getSharedPreferences("Enemigos", MODE_PRIVATE);
        String json = preferences.getString("enemigo", "");
        return DatosJson.fromJson(json);//Devuelve un ArrayList
    }


    //MENU LATERAL
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
