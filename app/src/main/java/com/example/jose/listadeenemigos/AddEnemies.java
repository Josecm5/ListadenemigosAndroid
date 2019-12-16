package com.example.jose.listadeenemigos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AddEnemies extends Fragment {

    //Imagen
    ImageView imagenEnemigo;

    //Nombre del enemigo
    EditText nombreEnemigo;

    /* Botones */
    Button button1;//Sacar foto
    Button button2;//Guardar datos enemigo

    Button botonGaleria;

    //Valoración
    RatingBar nivelEnemigo;

    FragmentManager fragmentManager;

    Context context;

    String imagenEnemy;

    //Galeria
    final int GALLERY_REQUEST = 4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add_enemies, container, false);

        imagenEnemigo = view.findViewById(R.id.imagen);
        nombreEnemigo = (EditText) view.findViewById(R.id.nombre);
        button1 = view.findViewById(R.id.botonFoto);
        button2 = view.findViewById(R.id.añadir);
        botonGaleria = view.findViewById(R.id.botonFotoGaleria);
        nivelEnemigo = view.findViewById(R.id.nivel);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST) {
            try {
                Uri uri = data.getData();
                // Leer fichero de la imagen
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                // Transformar los bits del fichero a un mapa de bits (imagen)
                Bitmap image = BitmapFactory.decodeStream(inputStream);
                imagenEnemigo.setImageBitmap(image);
                Uri image1 = saveImage(image);
                guardarImagen(image1);
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity(), "No se encuentra la foto", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void guardarImagen(Uri uri) {

        imagenEnemy = uri.toString();
        imagenEnemigo.setImageURI(uri);

    }
    
    protected Uri saveImage(Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), image, "Titulo", null);
        return Uri.parse( path);
    }

    /*
    * Con este metodo obtenemos el nombre del campo de texto y la valoracion
    * */
    void guardarDatos() {

        String nombre = nombreEnemigo.getText().toString();//Convertimos a string

        float nivel = nivelEnemigo.getRating();
        Datos enemigo = new Datos(imagenEnemy, nombre, nivel);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.enviarEnemigo(enemigo);
    }
}
