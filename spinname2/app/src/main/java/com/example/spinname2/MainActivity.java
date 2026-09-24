package com.example.spinname2;

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

    private Button btn_sortear;
    private EditText edt_lista_nomes;
    private TextView nome_sorteado;

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

        btn_sortear = findViewById(R.id.btn_sortear);
        edt_lista_nomes = findViewById(R.id.edt_lista_nomes);
        nome_sorteado = findViewById(R.id.nome_sorteado);

        btn_sortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listaDeNomes = edt_lista_nomes.getText().toString();
                String nomes[] = listaDeNomes.split(",");
                Random random = new Random();
                int num = random.nextInt(nomes.length);
                nome_sorteado.setText("Resultado: " + nomes[num]);



            }
        });
    }
}