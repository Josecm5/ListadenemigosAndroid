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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DesignList extends ArrayAdapter {

    Context context;
    int itemLayout;
    ArrayList<Datos> datosEnemigos;

    public DesignList(@NonNull Context context, int resource, @NonNull ArrayList<Datos> objects) {
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


        ImageView imagenEnemigo = convertView.findViewById(R.id.imagenLista);
        //Convertimos la imagen a uri
        if(datosEnemigos.get(position).image != null){
            Uri uriTexto = Uri.parse(datosEnemigos.get(position).image);
            imagenEnemigo.setImageURI(uriTexto);
        }



        //NOMBRE DEL ENEMIGO
        //Y la asignamos al imageView
        TextView nombreEnemigo = convertView.findViewById(R.id.textView1);

        if(nombreEnemigo != null){ //Si existe el elemento se le asignará al layout del XML
            nombreEnemigo.setText(datosEnemigos.get(position).nombre);
        }



        //VALORACION ENEMIGO


        TextView nivelEnemigo = convertView.findViewById(R.id.textView2);
        //Comprobamos si existe el elemento
        if(nivelEnemigo != null){
            float nivel = datosEnemigos.get(position).nivel;//Almacenamos el valor del rating del ArrayList
            String nivelString = Float.toString(nivel);//Convertimos a string
            nivelEnemigo.setText("Nivel: " + nivelString);
        }


        return convertView;
    }

}
