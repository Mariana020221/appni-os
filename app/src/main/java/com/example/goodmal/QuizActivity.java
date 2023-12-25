package com.example.goodmal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonNext;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ImageView imageViewSmall = findViewById(R.id.imageViewSmall);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonNext = findViewById(R.id.buttonNext);

        // Obtén la categoría de la actividad anterior
        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("category");

        // Filtra las preguntas por la categoría seleccionada
        questions = getQuestionsForCategory(category);

        if (!questions.isEmpty()) {
            showQuestion();
        } else {
            // Si no hay preguntas, puedes manejarlo de alguna manera (por ejemplo, mostrar un mensaje y finalizar la actividad)
            Toast.makeText(this, "No hay preguntas disponibles para esta categoría", Toast.LENGTH_SHORT).show();
            finish();
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    // Fin del cuestionario, pasa la puntuación a ResultadosActivity.
                    Intent resultadosIntent = new Intent(QuizActivity.this, ResultadosActivity.class);
                    score=score*2;
                    resultadosIntent.putExtra("score", score);
                    startActivity(resultadosIntent);
                    finish(); // Finaliza la actividad o realiza otras acciones.
                }
            }
        });
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            textViewQuestion.setText(currentQuestion.getQuestionText());

            radioGroupOptions.removeAllViews();
            String[] options = currentQuestion.getOptions();
            for (String option : options) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                radioGroupOptions.addView(radioButton);
            }
        }
    }

    private void checkAnswer() {
        if (currentQuestionIndex < questions.size()) {
            int selectedRadioButtonId = radioGroupOptions.getCheckedRadioButtonId();
            if (selectedRadioButtonId == -1) {
                // No se seleccionó ninguna respuesta.
                return;
            }

            int selectedOptionIndex = radioGroupOptions.indexOfChild(findViewById(selectedRadioButtonId));
            int correctOptionIndex = questions.get(currentQuestionIndex).getCorrectOptionIndex();

            if (selectedOptionIndex == correctOptionIndex) {
                score++; // Respuesta correcta.
            }
        }
    }

    private List<Question> getQuestionsForCategory(Category category) {
        // Filtra las preguntas por la categoría seleccionada
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question question : getQuestions()) {
            if (question.getCategory().getName().equals(category.getName())) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }


    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        // Preguntas de Matemáticas
        questions.add(new Question(Category.MATEMATICAS, "¿Cuánto es 2 + 2?", new String[]{"3", "4", "5"}, 1));
        questions.add(new Question(Category.MATEMATICAS, "¿Cuánto es 10 * 5?", new String[]{"50", "60", "70"}, 0));
        questions.add(new Question(Category.MATEMATICAS, "¿Cuál es la raíz cuadrada de 25?", new String[]{"4", "5", "6"}, 1));
        questions.add(new Question(Category.MATEMATICAS, "¿Cuántos grados tiene un ángulo recto?", new String[]{"45", "90", "180"}, 1));
        questions.add(new Question(Category.MATEMATICAS, "¿Cuánto es 3 al cubo?", new String[]{"6", "9", "27"}, 2));

// Preguntas de Cultura General
        questions.add(new Question(Category.CULTURA_GENERAL, "¿En qué año comenzó la Segunda Guerra Mundial?", new String[]{"1939", "1945", "1950"}, 0));
        questions.add(new Question(Category.CULTURA_GENERAL, "¿Quién escribió 'Cien años de soledad'?", new String[]{"Gabriel García Márquez", "Pablo Neruda", "Julio Cortázar"}, 0));
        questions.add(new Question(Category.CULTURA_GENERAL, "¿Cuál es la capital de Australia?", new String[]{"Sídney", "Melbourne", "Canberra"}, 2));
        questions.add(new Question(Category.CULTURA_GENERAL, "¿En qué continente se encuentra Egipto?", new String[]{"África", "Asia", "Europa"}, 0));
        questions.add(new Question(Category.CULTURA_GENERAL, "¿Quién pintó 'La Noche Estrellada'?", new String[]{"Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso"}, 0));

// Preguntas de Inglés
        questions.add(new Question(Category.INGLES, "What is the capital of the United Kingdom?", new String[]{"Paris", "London", "Berlin"}, 1));
        questions.add(new Question(Category.INGLES, "Which word is a synonym for 'happy'?", new String[]{"Sad", "Joyful", "Angry"}, 1));
        questions.add(new Question(Category.INGLES, "What is the past tense of 'eat'?", new String[]{"Eated", "Ate", "Eaten"}, 1));
        questions.add(new Question(Category.INGLES, "How many letters are there in the English alphabet?", new String[]{"24", "26", "28"}, 1));
        questions.add(new Question(Category.INGLES, "What is the plural form of 'child'?", new String[]{"Children", "Childs", "Childes"}, 0));

// Preguntas de Ciencias Naturales
        questions.add(new Question(Category.CIENCIAS_NATURALES, "¿Cuál es el proceso de fotosíntesis?", new String[]{"Producción de oxígeno", "Producción de glucosa", "Producción de agua"}, 1));
        questions.add(new Question(Category.CIENCIAS_NATURALES, "¿Cuántos huesos tiene el cuerpo humano?", new String[]{"206", "216", "226"}, 0));
        questions.add(new Question(Category.CIENCIAS_NATURALES, "¿Cuál es la función principal de los pulmones?", new String[]{"Circulación de la sangre", "Digestión", "Respiración"}, 2));
        questions.add(new Question(Category.CIENCIAS_NATURALES, "¿En qué año se descubrió la penicilina?", new String[]{"1928", "1938", "1948"}, 0));
        questions.add(new Question(Category.CIENCIAS_NATURALES, "¿Cuál es el planeta más grande del sistema solar?", new String[]{"Júpiter", "Saturno", "Neptuno"}, 0));


        return questions;
    }
}
