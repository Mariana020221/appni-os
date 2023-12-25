package com.example.goodmal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MenuActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mediaPlayer = MediaPlayer.create(this, R.raw.welcome);

        // Referencias a los botones
        Button btnMatematicas = findViewById(R.id.btnMatematicas);
        Button btnCulturaGeneral = findViewById(R.id.btnCulturaGeneral);
        Button btnIngles = findViewById(R.id.btnIngles);
        Button btnCienciasNaturales = findViewById(R.id.btnCienciasNaturales);

        // Configuración de listeners usando un método
        setupButtonClickListener(btnMatematicas, Category.MATEMATICAS);
        setupButtonClickListener(btnCulturaGeneral, Category.CULTURA_GENERAL);
        setupButtonClickListener(btnIngles, Category.INGLES);
        setupButtonClickListener(btnCienciasNaturales, Category.CIENCIAS_NATURALES);
    }

    private void setupButtonClickListener(Button button, final String category) {
        // Aquí debes convertir el String a Category si es necesario
        final Category categoryObject = new Category(category);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startQuizActivity(categoryObject);
            }
        });
    }

    private void startQuizActivity(Category category) {
        Intent intent = new Intent(MenuActivity.this, QuizActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void playClickSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
