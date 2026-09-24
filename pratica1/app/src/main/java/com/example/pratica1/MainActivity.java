package com.example.pratica1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText txt_auto_gasolina;
    private EditText txt_auto_alcool;
    private EditText txt_preco_gasolina;
    private EditText txt_preco_alcool;

    private Button btn_calcular;

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

        btn_calcular = findViewById(R.id.btn_calcular);

        txt_auto_gasolina = findViewById(R.id.txt_auto_gasolina);
        txt_auto_alcool = findViewById(R.id.txt_auto_alcool);

        txt_preco_gasolina = findViewById(R.id.txt_preco_gasolina);
        txt_preco_alcool = findViewById(R.id.txt_preco_gasolina);

        float eficiencia_gasolina = txt_auto_gasolina/txt_preco_gasolina;


    }




}