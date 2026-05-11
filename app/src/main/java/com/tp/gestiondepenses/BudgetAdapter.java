package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {

    private List<Budget> budgetList;
    private OnBudgetActionListener actionListener;

    public interface OnBudgetActionListener {
        void onEdit(Budget budget);
        void onDelete(Budget budget);
    }

    public BudgetAdapter(List<Budget> budgetList, OnBudgetActionListener listener) {
        this.budgetList = budgetList;
        this.actionListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_budget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Budget budget = budgetList.get(position);
        holder.textViewCategorie.setText(budget.getCategorie());
        holder.textViewPlafond.setText(String.format("%.2f DH", budget.getPlafond()));
        holder.textViewDepense.setText(String.format("%.2f DH", budget.getDepenseActuelle()));
        holder.textViewRestant.setText(String.format("%.2f DH restant", budget.getRestant()));

        double pourcentage = budget.getPourcentage();
        holder.progressBar.setProgress((int) pourcentage);
        holder.textViewPourcentage.setText(String.format("%.0f%%", pourcentage));

        // Changement de couleur selon le pourcentage
        if (pourcentage >= 90) {
            holder.progressBar.setProgressTintList(
                    android.content.res.ColorStateList.valueOf(0xFFD32F2F));
        } else if (pourcentage >= 70) {
            holder.progressBar.setProgressTintList(
                    android.content.res.ColorStateList.valueOf(0xFFFFA726));
        } else {
            holder.progressBar.setProgressTintList(
                    android.content.res.ColorStateList.valueOf(0xFF4CAF50));
        }

        holder.buttonEdit.setOnClickListener(v -> actionListener.onEdit(budget));
        holder.buttonDelete.setOnClickListener(v -> actionListener.onDelete(budget));
    }

    @Override
    public int getItemCount() {
        return budgetList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategorie, textViewPlafond, textViewDepense, textViewRestant, textViewPourcentage;
        ProgressBar progressBar;
        TextView buttonEdit, buttonDelete;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textViewCategorie = itemView.findViewById(R.id.textViewCategorie);
            textViewPlafond = itemView.findViewById(R.id.textViewPlafond);
            textViewDepense = itemView.findViewById(R.id.textViewDepense);
            textViewRestant = itemView.findViewById(R.id.textViewRestant);
            textViewPourcentage = itemView.findViewById(R.id.textViewPourcentage);
            progressBar = itemView.findViewById(R.id.progressBar);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}