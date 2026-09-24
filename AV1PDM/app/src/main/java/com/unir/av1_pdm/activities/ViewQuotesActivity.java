package com.unir.av1_pdm.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class ViewQuotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_quotes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Quote> quotes = (ArrayList<Quote>) getIntent().getSerializableExtra("quotes");

        RecyclerView recyclerQuotes = findViewById(R.id.recycler_quotes);
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerQuotes.setAdapter(new QuotesAdapter(this, quotes));





    }
}