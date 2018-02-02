package com.example.dalonsoperez.juegomapa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosQR extends AppCompatActivity {

    private Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_qr);
        TextView mostrar = (TextView) findViewById(R.id.TextoQR);
        intento = getIntent();
        String datos = intento.getExtras().getString("retorno");
        mostrar.setText(datos);
    }

}
