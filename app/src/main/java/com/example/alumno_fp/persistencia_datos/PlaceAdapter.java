package com.example.alumno_fp.persistencia_datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    public static class PlaceViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView textName;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            textName = itemView.findViewById(R.id.text_place);
        }
    }

    List<Place> places;
    Context context;

    PlaceAdapter(List<Place> places,Context context){
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        final PlaceViewHolder pvh = new PlaceViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.textName.setText(places.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void deleteItem(int i){
        places.remove(i);
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Place getPlace(int i){
        return places.get(i);
    }
}
