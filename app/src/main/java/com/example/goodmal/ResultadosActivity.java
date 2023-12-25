package com.example.goodmal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("score")) {
            int score = intent.getIntExtra("score", 0);
            // Usa el valor de score como sea necesario en ResultadosActivity
            // Puedes mostrarlo en un TextView, por ejemplo.
            TextView textViewScore = findViewById(R.id.textViewScore);
            textViewScore.setText("Puntuación: " + score);

            // Configura el OnClickListener para el botón btnIrAMenu
            Button btnIrAMenu = findViewById(R.id.btnIrAMenu);
            btnIrAMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Inicia la actividad ActivityMenu
                    Intent menuIntent = new Intent(ResultadosActivity.this, MenuActivity.class);
                    startActivity(menuIntent);
                    // Cierra la actividad actual si es necesario
                    finish();
                }
            });
        }
    }
}

