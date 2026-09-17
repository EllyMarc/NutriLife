package com.example.gellato_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pedido_Activity extends AppCompatActivity {

    private TextView textResult;
    private Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textResult = findViewById(R.id.text_Result);
        btn_voltar = findViewById(R.id.btn_voltar);

        Intent intent = getIntent();
        String tipo = intent.getStringExtra("tipo");
        String sabor = intent.getStringExtra("sabor");
        int quantidade = intent.getIntExtra("quantidade", 0);
        double valor = 0.0;

        if (tipo == "Cone"){
            valor = 5.50;
        } else if (tipo == "Cone duplo") {
            valor = 10.0;
        } else if (tipo == "Picolé") {
            valor = 3.0;
        } else {
            valor = 8.50;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Sabor: ").append(sabor).append("\n");
        sb.append("Quantidade: ").append(quantidade).append("\n");
        sb.append("Valor total:: ").append(valor*quantidade);

        textResult.setText(sb.toString());

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}