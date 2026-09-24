package com.example.dice;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private ImageView imageView;

    private void atualizarDado(int num){

        switch (num){
            case 1:
                imageView.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dice6);
                break;

        }

    }

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


        button =findViewById(R.id.button_rolar);
        textView =findViewById(R.id.texto_resultado);
        imageView = findViewById((R.id.dice));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int num = random.nextInt(6) + 1;
                textView.setText("");

                RotateAnimation giro = new RotateAnimation(0f, 720f, imageView.getPivotX(), imageView.getPivotY());

                giro.setInterpolator(new LinearInterpolator());
                giro.setRepeatCount(Animation.ABSOLUTE);
                giro.setDuration(1000);
                imageView.startAnimation(giro);

                new android.os.CountDownTimer(1000,100){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int numtemp = random.nextInt(6) + 1;
                        atualizarDado(numtemp);
                    }

                    @Override
                    public void onFinish() {
                        atualizarDado(num);
                    }
                }.start();

            }
        });
    }
}