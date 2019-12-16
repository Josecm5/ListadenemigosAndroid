package com.example.jose.listadeenemigos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TopEnemies extends Fragment {

    DesignList design;
    TextView texto;
    DatosJson datosEnemigos;

    ListView lista;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_top5, container, false);
        texto = view.findViewById(R.id.textView1);

        //Accedemos al mainActivity
        MainActivity mainActivity = (MainActivity) getActivity();

        //asignamos la clase enemigos
        datosEnemigos = new DatosJson();

        datosEnemigos = mainActivity.recibirDatos();//Obtenemos enemigos del arraylist del mainactivity


        lista = view.findViewById(R.id.lista);

        //Aqui asignamos el adapter para el fragment
        design = new DesignList(this.getActivity(), R.layout.activity_design_list, datosEnemigos);
        lista.setAdapter(design);

        return view;
    }


}
