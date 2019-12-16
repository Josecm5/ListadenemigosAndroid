package com.example.jose.listadeenemigos;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PhotosEnemies extends Fragment {

    Gallery photos;
    ImageView imagenEnemigo;
    DatosJson datosEnemigo;

    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photos_enemies, container, false);

        imagenEnemigo = view.findViewById(R.id.imagen);

        MainActivity mainActivity = (MainActivity) getActivity();

        datosEnemigo = new DatosJson();

        datosEnemigo = mainActivity.recibirDatos();

        gridView = view.findViewById(R.id.gridView);

        photos = new Gallery(this.getActivity(), R.layout.activity_gallery, datosEnemigo);
        gridView.setAdapter(photos);

        return view;
    }
}
