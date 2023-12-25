package com.example.goodmal;

import android.os.Bundle;
import com.example.goodmal.Category;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        // Crear una lista de categorías estática
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Matemáticas"));
        categories.add(new Category("Cultura General"));
        categories.add(new Category("Español"));
        categories.add(new Category("Ciencia"));

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        categoryAdapter = new CategoryAdapter(categories, this::onCategoryClicked);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategories.setAdapter(categoryAdapter);
    }

    // Método para manejar el clic en una categoría
    private void onCategoryClicked(Category category) {
        // Aquí puedes iniciar la siguiente actividad para mostrar el cuestionario de esa categoría
        // Puedes pasar la categoría seleccionada a la actividad del cuestionario.
    }

    // Método de ejemplo para cargar categorías (debes implementar tu propia lógica para cargar categorías)
    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Matemáticas"));
        categories.add(new Category("Cultura General"));
        categories.add(new Category("Español"));
        categories.add(new Category("Ciencia"));
        return categories;
    }
}
