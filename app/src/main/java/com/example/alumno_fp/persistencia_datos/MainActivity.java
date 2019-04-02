package com.example.alumno_fp.persistencia_datos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    ListView listPlaces;
    Button buttonPlaces;
    Places placesList;
    ArrayAdapter<Place> mAdapter;
    private final int CODE_SAVE  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        buttonPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SaveActivity.class);
                startActivityForResult(intent,CODE_SAVE);
            }
        });

        /*buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("placesPreferences",Context.MODE_PRIVATE);
                String json = prefs.getString("places","default");
                placesList = placesList.fromJson(json);

                mAdapter = new PlaceAdapter(v.getContext(),placesList.getPlaces());
                listPlaces.setAdapter(mAdapter);
            }
        });*/
    }

    private void initUI(){
        listPlaces = findViewById(R.id.list_places);
        buttonPlaces = findViewById(R.id.button_places);
        placesList = new Places();

        SharedPreferences prefs = getSharedPreferences("placesPreferences",Context.MODE_PRIVATE);
        String json = prefs.getString("places","default");

        if (!json.equals("default")){
            placesList = placesList.fromJson(json);
        }

        mAdapter = new PlaceAdapter(this,placesList.getPlaces());
        listPlaces.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == CODE_SAVE){
            String place = data.getStringExtra("Place");
            placesList.addPlace(new Place(place));
            mAdapter.notifyDataSetChanged();
            String json = placesList.toJson();

            SharedPreferences prefs = getSharedPreferences("placesPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("places",json);
            editor.apply();
        }
    }
}
