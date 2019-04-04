package com.example.alumno_fp.persistencia_datos;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class Swipe extends ItemTouchHelper.SimpleCallback {

    private PlaceAdapter mAdapter;
    private SharedPreferences prefs;

    public Swipe(PlaceAdapter adapter,SharedPreferences prefs){
        super(0,ItemTouchHelper.LEFT);
        mAdapter = adapter;
        this.prefs = prefs;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();

        Places placesList = new Places(mAdapter.getPlaces());
        placesList.removePlace(mAdapter.getPlace(position));

        String json = placesList.toJson();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("places",json);
        editor.apply();

        mAdapter.notifyDataSetChanged();
    }
}
