package com.example.alumno_fp.persistencia_datos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    RecyclerView listPlaces;
    Button buttonPlaces;
    Places placesList;
    PlaceAdapter mAdapter;
    private final int CODE_SAVE  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initAdapter();
        Log.i("List",placesList.getPlaces().toString());

        buttonPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SaveActivity.class);
                startActivityForResult(intent,CODE_SAVE);
            }
        });
    }

    private void initUI(){
        buttonPlaces = findViewById(R.id.button_places);
        placesList = new Places();

        listPlaces = findViewById(R.id.list_places);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listPlaces.setLayoutManager(llm);
        listPlaces.setHasFixedSize(true);

        SharedPreferences prefs = getSharedPreferences("placesPreferences",Context.MODE_PRIVATE);
        String json = prefs.getString("places","default");

        if (!json.equals("default")){
            placesList = placesList.fromJson(json);
        }
    }

    private void initAdapter(){
        mAdapter = new PlaceAdapter(placesList.getPlaces(),MainActivity.this);
        listPlaces.setAdapter(mAdapter);

        SharedPreferences prefs = getSharedPreferences("placesPreferences",Context.MODE_PRIVATE);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new Swipe(mAdapter,prefs));
        itemTouchHelper.attachToRecyclerView(listPlaces);
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
