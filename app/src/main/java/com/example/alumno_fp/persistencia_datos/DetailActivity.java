package com.example.alumno_fp.persistencia_datos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();

        initData();
    }

    private void initUI(){
        textDetail = findViewById(R.id.text_detail);
    }

    private void initData(){
        Intent intent = getIntent();
        textDetail.setText(intent.getStringExtra("Name"));
    }
}
