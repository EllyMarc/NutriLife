package com.unir.av1_pdm.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unir.av1_pdm.R;
import com.unir.av1_pdm.models.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Quote> favoriteQuotes = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("quotes", MODE_PRIVATE);

        int num_salvos = sharedPreferences.getInt("num_salvos",0);

        for (int i =0; i<num_salvos;i++){
            Quote quote = new Quote("Placeholder","Placeholder",sharedPreferences.getString(String.valueOf(i),""));
            favoriteQuotes.add(quote);
        }


        RecyclerView recyclerQuotes = findViewById(R.id.recyclerViewFavoritos);
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerQuotes.setAdapter(new FavoritesAdapter(this, favoriteQuotes));

    }
}