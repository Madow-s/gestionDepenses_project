package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        TextView nom, couleur;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.item_nom_categorie);
            couleur = itemView.findViewById(R.id.item_couleur_categorie);
            image = itemView.findViewById(R.id.CategorieImage);
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

        holder.couleur.setText(c.getCouleur());

        String icone = c.getIcone();

        if (icone.equals("food")) {
            holder.image.setImageResource(R.drawable.ic_launcher_round);
        }
        else {
            holder.image.setImageResource(R.drawable.ic_launcher_round);
        }

    }


    @Override
    public int getItemCount() {
        return liste.size();
    }
}
