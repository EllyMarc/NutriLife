package com.example.gellato_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private RadioGroup radioGroup;
    private EditText edt_quantidade;
    private Button btn_finalizar;

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

        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        edt_quantidade = findViewById(R.id.edt_quantidade);
        btn_finalizar = findViewById(R.id.btn_finalizar);

        String opcoes[] = {"Cone", "Cone duplo", "Picolé", "Sundae"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_quantidade.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Informe a quantidade!", Toast.LENGTH_SHORT).show();

                }else {
                    int quantidade = Integer.parseInt(edt_quantidade.getText().toString());
                    String tipo = spinner.getSelectedItem().toString();
                    int selecionado = radioGroup.getCheckedRadioButtonId();
                    RadioButton rb = findViewById(selecionado);
                    String sabor = rb.getText().toString();

                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("Finalizar Pedido");
                    janela.setMessage("Feseja Finalizar?");
                    janela.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Pedido_Activity.class);
                            intent.putExtra("tipo", tipo);
                            intent.putExtra("sabor", sabor);
                            intent.putExtra("quantidade", quantidade);
                            startActivity(intent);
                        }
                    });

                    janela.setNegativeButton("Não", null);
                    janela.show();

                }
            }
        });


    }
}