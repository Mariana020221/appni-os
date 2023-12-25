package com.example.goodmal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaginaPrincipalActivity extends AppCompatActivity {

    private TextView textViewBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        // Inicializa el TextView de bienvenida
        textViewBienvenida = findViewById(R.id.textViewBienvenida);

        // Recupera el nombre de usuario (deberás ajustar cómo obtienes este valor)
        String nombreUsuario = obtenerNombreDeUsuario();

        // Muestra la bienvenida personalizada
        textViewBienvenida.setText("Bienvenide, " + nombreUsuario);

        // Configura el clic del botón "Iniciar actividades"
        Button btnIniciarActividades = findViewById(R.id.botonIniciarActividades);
        btnIniciarActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad del menú
                Intent menuIntent = new Intent(PaginaPrincipalActivity.this, MenuActivity.class);
                startActivity(menuIntent);
            }
        });
    }

    // Este método simula la obtención del nombre de usuario (deberás reemplazarlo con la lógica real)
    private String obtenerNombreDeUsuario() {
        SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString("nombreUsuario", "UsuarioEjemplo");
    }
}
