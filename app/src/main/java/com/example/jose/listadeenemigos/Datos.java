package com.example.jose.listadeenemigos;

import android.widget.ImageView;

public class Datos {

    String image;
    String nombre;
    float nivel;

    Datos(String imagen, String nombre, float nivel) {

        this.image = imagen;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNivel() {
        return nivel;
    }

    public void setNivel(float nivel) {
        this.nivel = nivel;
    }
}
