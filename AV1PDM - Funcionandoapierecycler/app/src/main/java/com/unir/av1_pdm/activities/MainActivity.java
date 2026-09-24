package com.unir.av1_pdm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unir.av1_pdm.R;
import com.unir.av1_pdm.models.Quote;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_buscar= findViewById(R.id.btnBuscar);
        EditText txt_buscar_autor = findViewById(R.id.etTags);

        btn_buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                String query = txt_buscar_autor.getText().toString();
                Toast.makeText(MainActivity.this, "Autor buscado: " + query,Toast.LENGTH_SHORT).show();
                query(query);

            }
        });

    }

    private void query(String authorName){

        String url = "https://programming-quotes-api-pi.vercel.app/quotes/author/"+authorName;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if(response.isSuccessful()){
                    String result = response.body().string();
                    //Log.d("MainActivity","Request feita:" +url);
                    Log.d("MainActivity","Resposta:" + result);

                    Gson gson = new Gson();

                    Type listType = new TypeToken<ArrayList<Quote>>(){}.getType();
                    final List<Quote> quotes = gson.fromJson(result, listType);

                    for (Quote quote : quotes){
                        Log.d("MainActivity",quote.getId());
                        Log.d("MainActivity",quote.getAuthor());
                        Log.d("MainActivity",quote.getText());
                    }

                    runOnUiThread(() -> {
                        Intent intent =new Intent(MainActivity.this, ViewQuotesActivity.class);
                        intent.putExtra("quotes",(Serializable) quotes);
                        startActivity(intent);

                    });





                }

            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }


        });
    }
}