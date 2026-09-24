package com.unir.av1_pdm.activities;


import static java.lang.reflect.Modifier.PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unir.av1_pdm.R;
import com.unir.av1_pdm.models.Quote;
import com.unir.av1_pdm.utils.TranslateHelper;

import java.io.Serializable;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {
    private List<Quote> quotes; //Quote objects array (data source)
    private Context context;

    public QuotesAdapter(Context context, List<Quote> quotes) {
        this.context = context;
        this.quotes = quotes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView quoteText;
        public TextView author;
        public Button btn_salvar;

        public ViewHolder(View itemView) {
            super(itemView);
            quoteText = itemView.findViewById(R.id.txtContent);
            author = itemView.findViewById(R.id.txtAuthor);
            btn_salvar = itemView.findViewById(R.id.btnSalvar);
        }
    }

    @NonNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Quote quote = quotes.get(position);

        holder.author.setText(quote.getAuthor());
        holder.quoteText.setText("Carregando...");

        TranslateHelper.translate(quote.getText(), new TranslateHelper.OnTranslateListener() {
            @Override
            public void onSuccess(String translatedText) {

                 String textoCorrigido = translatedText.replace("{translation:","");
                 textoCorrigido = textoCorrigido.replace("+"," ");
                textoCorrigido = textoCorrigido.replace("}"," ");


                holder.quoteText.setText(textoCorrigido);
            }

            @Override
            public void onError(String errorMessage) {
                holder.quoteText.setText(quote.getText());
            }
        });

        holder.btn_salvar.setText("Salvar");

        holder.btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.widget.Toast.makeText(v.getContext(), "Citação salva!", android.widget.Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = context.getSharedPreferences("quotes", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int num_salvos = sharedPreferences.getInt("num_salvos",0);

                editor.putString(String.valueOf(num_salvos),quote.getText() +"  -" +quote.getAuthor());
                editor.putInt("num_salvos",num_salvos+1);

                editor.apply();

            }
        });


    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
