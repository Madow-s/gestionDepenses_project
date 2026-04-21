package com.tp.gestiondepenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.List;

public class AdapterDepense extends RecyclerView.Adapter<ReclyclerViewDepense> {

    Context context;
    List<Depense> items;


    public AdapterDepense (Context context,  List<Depense> items ){
        this.context= context;
        this.items= items;
    }


    @NonNull
    @Override
    public ReclyclerViewDepense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReclyclerViewDepense(LayoutInflater.from(context).inflate(R.layout.item_depense,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReclyclerViewDepense holder, int position) {
        holder.CategorieView.setText(String.valueOf(items.get(position).getCategorie_id()));
        holder.MontantView.setText(String.valueOf(items.get(position).getMontant()));
        holder.MPaiementView.setText(items.get(position).getMoyen_paiement());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
