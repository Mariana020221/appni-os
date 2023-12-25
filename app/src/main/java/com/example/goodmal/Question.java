package com.example.goodmal;

import java.io.Serializable;

public class Question implements Serializable {
    private Category category;
    private String questionText;
    private String[] options;
    private int correctOptionIndex;
    private long id;

    public Question(String categoryName, String questionText, String[] options, int correctOptionIndex) {
        this.category = new Category(categoryName);
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public Category getCategory() {
        return category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
