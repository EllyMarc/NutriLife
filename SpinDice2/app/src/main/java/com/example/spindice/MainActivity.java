package com.example.spindice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button_TextView, button_EditText, button_Radio, button_CheckBox;
    private EditText editText;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    private Spinner spinner;

    private ProgressBar progressBar2;
    private RatingBar ratingBar;
    private Handler handler;
    private int progresso;



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

        textView = findViewById(R.id.textView_resultado);
        button_TextView = findViewById(R.id.button_TextView);
        editText = findViewById(R.id.editText);
        button_EditText = findViewById(R.id.button_Radio);
        radioGroup = findViewById(R.id.radioGroup);
        button_Radio = findViewById(R.id.button_Radio);
        checkBox = findViewById(R.id.checkBox6);
        checkBox1 = findViewById(R.id.checkBox7);
        checkBox2 = findViewById(R.id.checkBox8);
        button_CheckBox = findViewById(R.id.Button_CheckBox);
        spinner = findViewById(R.id.spinner);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setProgress(10);
        progresso = 1;
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);

        button_TextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                textView.setText("Texto alterado pelo botão!");
                Log.d("Visualizador texto", textView.getText().toString());
                Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT ).show();

            }
        });

        button_EditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String texto = editText.getText().toString();

                if (texto.isBlank()) {
                    Toast.makeText(MainActivity.this, "Insira um texto", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Texto inserido: " + texto, Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_Radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(MainActivity.this, "Opção selecionada " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(MainActivity.this, "Opção selecionada " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        button_CheckBox.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                StringBuilder stringBuilder = new StringBuilder("Checkboxes selecionadas: ");
                if (checkBox.isChecked()){
                    stringBuilder.append("Opção 1 ");
                }

                if (checkBox1.isChecked()){
                    stringBuilder.append("Opção 2 ");
                }

                if (checkBox2.isChecked()){
                    stringBuilder.append("Opção 3 ");
                }
            }
        });


        button_CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(MainActivity.this, "Opção 1 selecionada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Opção 1 selecionada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Opção 1 selecionada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String [] opcoes = {"Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira",
                "Quinta-feira", "Sexta-feira", "Sábado"};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opcoes);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dia = adapterView.getItemAtPosition(1). toString();
                Toast.makeText(MainActivity.this, "Dia selecionado: ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        int tempoEspera = 3000;
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Carregamento concluído!", Toast.LENGTH_SHORT).show();
            }
        }, tempoEspera);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float nota, boolean b) {
                Toast.makeText(MainActivity.this, "Nota selecionada: " +  nota, Toast.LENGTH_SHORT).show();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresso < 100){
                    progresso += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar2.setProgress(progresso);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}