package com.example.jose.listadeenemigos;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Gallery extends ArrayAdapter {

    Context context;
    int itemLayout;
    ArrayList<Datos> datosEnemigos;

    public Gallery(@NonNull Context context, int resource, @NonNull ArrayList<Datos> objects) {
        super(context, resource, objects);
        this.context = context;
        itemLayout = resource;
        datosEnemigos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, parent, false);

        }

        ImageView imagenGaleria = convertView.findViewById(R.id.imagenGaleria);
        if(datosEnemigos.get(position).image != null) {
            Uri uriGaleria = Uri.parse(datosEnemigos.get(position).image);
            imagenGaleria.setImageURI(uriGaleria);
        }

        return convertView;
    }
}
