package com.example.dalonsoperez.juegomapa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empezar = (Button)findViewById(R.id.Comenzar);
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAlMapa();
            }
        });
    }

    private void irAlMapa(){
        Intent pantallaMapa = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(pantallaMapa);
    }
}
