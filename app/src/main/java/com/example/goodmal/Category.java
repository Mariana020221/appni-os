package com.example.goodmal;

import java.io.Serializable;

public class Category implements Serializable {
    public static final String MATEMATICAS = "Matemáticas";
    public static final String CULTURA_GENERAL = "Cultura General";
    public static final String INGLES = "Inglés";
    public static final String CIENCIAS_NATURALES = "Ciencias Naturales";

    private long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
