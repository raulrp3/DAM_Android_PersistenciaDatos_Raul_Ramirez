package com.example.alumno_fp.persistencia_datos;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Places {

    private List<Place> placesList;

    public Places(){
        placesList = new ArrayList<>();
    }

    public Places(List<Place> places) { placesList = places;}

    public List<Place> getPlaces(){
        return placesList;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Places fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Places.class);
    }

    public void addPlace(Place place){
        placesList.add(place);
    }

    public void removePlace(Place place){
        placesList.remove(place);
    }

    public Place getPlace(int i){
        return placesList.get(i);
    }

    @Override
    public String toString() {
        return "Places{" +
                "placesList=" + placesList +
                '}';
    }
}
