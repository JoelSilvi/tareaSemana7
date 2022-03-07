package com.example.sistemaatencin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ResultadoActivity extends AppCompatActivity {

    private TextView txtMsg, txtRandom;

    Random r = new Random();
    int i1 = 1 + r.nextInt( 20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtMsg = (TextView)findViewById(R.id.txtMsg);
        String etRut = getIntent().getStringExtra("rut");
        txtMsg.setText("Usuario con Rut: " + etRut);

        txtRandom = (TextView)findViewById(R.id.txtRandom);
        String numRandom = String.valueOf(i1);
        txtRandom.setText("Se le asignado el puesto: " + numRandom);
    }
}