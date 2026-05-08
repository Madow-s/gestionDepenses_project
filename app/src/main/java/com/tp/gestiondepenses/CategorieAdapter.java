package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Categorie;
import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> {

    List<Categorie> liste;

    public CategorieAdapter(List<Categorie> liste) {
        this.liste = liste;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText nom , icone , couleur;
        Switch switchDefaut;
        View view_couleur;
        public ViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.CategorieName);
            icone = itemView.findViewById(R.id.CategorieIcon);
            view_couleur = itemView.findViewById(R.id.viewCouleur);
            couleur = itemView.findViewById(R.id.CategorieCouleur);
            switchDefaut = itemView.findViewById(R.id.switchDefaut);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorie c = liste.get(position);

        holder.nom.setText(c.getNom());

        holder.icone.setText(c.getIcone());

        holder.couleur.setText(c.getCouleur());

        holder.switchDefaut.setChecked(c.isEst_defaut());

    }


    @Override
    public int getItemCount() {
        return liste.size();
    }
}
