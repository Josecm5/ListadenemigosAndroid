package com.example.jose.listadeenemigos;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DatosJson extends ArrayList<Datos> {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    static public DatosJson fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DatosJson.class);
    }
}
