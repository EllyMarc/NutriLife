package com.unir.av1_pdm.activities;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unir.av1_pdm.R;
import com.unir.av1_pdm.models.Quote;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {
    private List<Quote> quotes; //Quote objects array (data source)

    public QuotesAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView quoteText;
        public TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            quoteText = itemView.findViewById(R.id.txtContent);
            author = itemView.findViewById(R.id.txtAuthor);
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

        holder.quoteText.setText(quote.getText());
        holder.author.setText(quote.getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
