package com.example.goodmal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextEdad;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mediaPlayer = MediaPlayer.create(this, R.raw.welcome);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEdad = findViewById(R.id.editTextEdad);

        Button btnCerrarApp = findViewById(R.id.btnCerrarApp);
        btnCerrarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cierra la aplicación
                finish();
            }
        });

        Button botonRegistro = findViewById(R.id.botonRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = editTextNombre.getText().toString();
                String edadStr = editTextEdad.getText().toString();

                if (nombreUsuario.isEmpty() || edadStr.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return; // Detén el proceso de registro
                } else if (tieneNumeros(nombreUsuario)) {
                    Toast.makeText(RegistroActivity.this, "El nombre de usuario no debe contener números", Toast.LENGTH_SHORT).show();
                    return; // Detén el proceso de registro
                }

                int edad;
                try {
                    edad = Integer.parseInt(edadStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(RegistroActivity.this, "La edad debe ser un número válido", Toast.LENGTH_SHORT).show();
                    return; // Detén el proceso de registro
                }

                if (edad < 6 || edad > 12) {
                    Toast.makeText(RegistroActivity.this, "Lo siento, la aplicación está diseñada para niños de 6 a 12 años", Toast.LENGTH_SHORT).show();
                    return; // Detén el proceso de registro
                }

                // Resto del código para procesar el registro
                // Guarda el nombre de usuario en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombreUsuario", nombreUsuario);
                editor.apply();

                // Redirige al usuario a la actividad principal
                Intent intent = new Intent(RegistroActivity.this, PaginaPrincipalActivity.class);
                startActivity(intent);
                playClickSound();
            }

            // Función para verificar si hay números en el nombre de usuario
            private boolean tieneNumeros(String texto) {
                // Utiliza una expresión regular para verificar si hay dígitos en el texto
                return texto.matches(".*\\d.*");
            }


        });


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
