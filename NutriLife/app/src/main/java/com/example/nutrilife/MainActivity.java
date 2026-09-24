package com.example.nutrilife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editText_nome;
    private EditText editText_peso;
    private EditText editText_altura;
    private EditText editText_cintura;
    private EditText editText_quadril;
    private RadioGroup radioGroup;
    private RadioButton rb_masculino;
    private RadioButton rb_feminino;
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

        editText_nome = findViewById(R.id.editText_nome);
        editText_peso = findViewById(R.id.editText_peso);
        editText_altura = findViewById(R.id.editText_altura);
        editText_cintura = findViewById(R.id.editText_cintura);
        editText_quadril = findViewById(R.id.editText_quadril);
        radioGroup = findViewById(R.id.radioGroupSexo);
        rb_feminino= findViewById(R.id.radioButtonFeminino);
        rb_masculino=findViewById(R.id.radioButtonMasculino);
        btn_calcular = findViewById(R.id.button_calcular);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String nome = editText_nome.getText().toString();
                String pesoString = editText_peso.getText().toString();
                String alturaString = editText_altura.getText().toString();
                String cinturaString = editText_cintura.getText().toString();
                String quadrilString = editText_quadril.getText().toString();
                String sexo = "";
                int opcao = radioGroup.getCheckedRadioButtonId();

                if (nome.isEmpty() || pesoString.isEmpty() || alturaString.isEmpty() || cinturaString.isEmpty() || quadrilString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();return;
                } else {

                    if (opcao == R.id.radioButtonMasculino) {
                        sexo = "Masculino";
                    } else if (opcao == R.id.radioButtonFeminino) {
                        sexo = "Feminino";
                    } else {
                        Toast.makeText(MainActivity.this, "Por favor, selecione o sexo!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                try {
                    // Conversão segura dentro de um try-catch para evitar erros se houver caracteres inválidos
                    double peso = Double.parseDouble(pesoString);
                    double altura = Double.parseDouble(alturaString);
                    double cintura = Double.parseDouble(cinturaString);
                    double quadril = Double.parseDouble(quadrilString);

                    Intent intent = new Intent(MainActivity.this, Tela2.class);
                    intent.putExtra("nome", nome);
                    intent.putExtra("peso", peso);
                    intent.putExtra("altura", altura);
                    intent.putExtra("cintura", cintura);
                    intent.putExtra("quadril", quadril);
                    intent.putExtra("sexo", sexo);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Por favor, insira números válidos nos campos numéricos.", Toast.LENGTH_SHORT).show(); return;
                }


            }
        });
    }
}

