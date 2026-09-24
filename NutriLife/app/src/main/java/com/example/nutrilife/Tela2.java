package com.example.nutrilife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class Tela2 extends AppCompatActivity {
    private TextView textView_nome;
    private TextView textView_imc;
    private TextView textView_riscos;
    private TextView textViewClassificacao;
    private TextView textView_rcq;
    private TextView textViewRcqClassif;
    private Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView_nome = findViewById(R.id.textView_nome);
        textView_imc = findViewById(R.id.textViewValorImc);
        textViewClassificacao = findViewById(R.id.textViewClassificacao);
        textView_riscos = findViewById(R.id.textView_riscos);
        textView_rcq = findViewById(R.id.textView_rcq);
        btn_voltar = findViewById(R.id.button_voltar);
        textViewRcqClassif = findViewById(R.id.textView_rcqClassific);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String sexo = intent.getStringExtra("sexo");
        double peso = intent.getDoubleExtra("peso", 0);
        double cintura = intent.getDoubleExtra("cintura", 0);
        double altura = intent.getDoubleExtra("altura", 0);
        double quadril = intent.getDoubleExtra("quadril",0);

        double rcq = cintura / quadril;
        double imc= peso / (altura*altura);

        StringBuilder sb_nome = new StringBuilder();
        sb_nome.append(nome).append("!");
        textView_nome.setText(sb_nome.toString());

        textView_imc.setText(String.format("%.1f", imc));

        if (imc < 17.0){
            textViewClassificacao.setText("Muito abaixo do peso");
            textView_riscos.setText("Maior risco de problemas de saúde, deficiências nutricionais,\n" +
                    "redução do desempenho físico e fraqueza/letargia");

        } else if (imc >= 17.0 && imc < 18.5){
            textViewClassificacao.setText("Abaixo do peso");
            textView_riscos.setText("Maior risco de problemas relacionados ao baixo peso e deficiências\n" +
                    "nutricionais");

        } else if (imc >= 18.5 && imc <25){
            textViewClassificacao.setText("Peso normal");
            textView_riscos.setText("Faixa de peso considerada adequada para a maioria dos adultos");

        }else if (imc >= 25 && imc <30){
            textViewClassificacao.setText("Sobrepeso");
            textView_riscos.setText("Maior risco de alterações metabólicas, diabetes tipo 2 e doenças\n" +
                    "cardiovasculares");

        }else if (imc >= 30 && imc <35){
            textViewClassificacao.setText("Obesidade grau I");
            textView_riscos.setText("Risco elevado de diabetes tipo 2, hipertensão e doenças\n" +
                    "cardiovasculares");

        }else if (imc >= 35 && imc <40){
            textViewClassificacao.setText("Obesidade grau II");
            textView_riscos.setText("Risco muito elevado de complicações metabólicas, cardiovasculares\n" +
                    "e respiratórias");

        }else if (imc >= 40){
            textViewClassificacao.setText("Obesidade grau III");
            textView_riscos.setText("Risco muitíssimo elevado de comorbidades e comprometimento da\n" +
                    "saúde e qualidade de vida");

        }

        textView_rcq.setText(String.format("%.1f", rcq));

        if (sexo.equalsIgnoreCase("Masculino")) {
            if (rcq < 0.9) {
                textViewRcqClassif.setText("Normal");
            } else {
                textViewRcqClassif.setText("Risco aumentado");
            }
        } else if (sexo.equalsIgnoreCase("Feminino")) {
            if (rcq < 0.85) {
                textViewRcqClassif.setText("Normal");
            } else {
                textViewRcqClassif.setText("Risco aumentado");
            }
        }

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View telaInicio) {
                finish(); // Fecha a Tela2 e retorna para a MainActivity
            }
        });




    }
}