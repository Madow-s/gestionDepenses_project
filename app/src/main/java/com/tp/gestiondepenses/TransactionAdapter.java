package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction t = transactions.get(position);
        holder.tvDesc.setText(t.getDescription());
        holder.tvMontant.setText(String.format("%.2f €", t.getMontant()));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDesc, tvMontant;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tv_description);
            tvMontant = itemView.findViewById(R.id.tv_montant);
        }
    }
}