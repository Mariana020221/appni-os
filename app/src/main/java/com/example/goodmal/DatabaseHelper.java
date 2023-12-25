package com.example.goodmal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "goodmal_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Definir la estructura de la tabla de preguntas
        String createQuestionsTable = "CREATE TABLE questions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "category TEXT, "
                + "question_text TEXT, "
                + "option_1 TEXT, "
                + "option_2 TEXT, "
                + "option_3 TEXT, "
                + "correct_option INTEGER)";
        db.execSQL(createQuestionsTable);

        // Definir la estructura de la tabla de categorías
        String createCategoriesTable = "CREATE TABLE categories ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT)";
        db.execSQL(createCategoriesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes manejar actualizaciones futuras de la base de datos si es necesario.
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("category", question.getCategory().getName());  // Cambio en la columna category
        values.put("question_text", question.getQuestionText());
        values.put("option_1", question.getOptions()[0]);
        values.put("option_2", question.getOptions()[1]);
        values.put("option_3", question.getOptions()[2]);
        values.put("correct_option", question.getCorrectOptionIndex());

        long questionId = db.insert("questions", null, values);

        question.setId(questionId);

        db.close();
    }

    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", category.getName());

        long categoryId = db.insert("categories", null, values);

        category.setId(categoryId);

        db.close();
    }

    public List<Question> getQuestionsByCategory(Category category) {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("questions", null, "category=?", new String[]{category.getName()}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String questionText = cursor.getString(cursor.getColumnIndex("question_text"));
                String option1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option3 = cursor.getString(cursor.getColumnIndex("option_3"));
                int correctOption = cursor.getInt(cursor.getColumnIndex("correct_option"));

                Question question = new Question(
                        category.getName(), // Cambio aquí
                        questionText,
                        new String[]{option1, option2, option3},
                        correctOption
                );

                question.setId(id);
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return questionList;
    }



    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("categories", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");

            // Verificar si las columnas existen en el resultado de la consulta
            if (idIndex >= 0 && nameIndex >= 0) {
                do {
                    long id = cursor.getLong(idIndex);
                    String name = cursor.getString(nameIndex);

                    Category category = new Category(name);
                    category.setId(id);
                    categoryList.add(category);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();
        return categoryList;
    }

}
