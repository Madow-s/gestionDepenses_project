package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Categorie;
import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.List;

public class DepenseAdapter extends RecyclerView.Adapter<DepenseAdapter.ViewHolder> {

    List<Depense> liste;
    List<Categorie> categories;

    public DepenseAdapter(
            List<Depense> liste,
            List<Categorie> categories
    ) {
        this.liste = liste;
        this.categories = categories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView montant, categorie, description;

        public ViewHolder(View itemView) {
            super(itemView);

            montant =
                    itemView.findViewById(R.id.montant);

            categorie =
                    itemView.findViewById(R.id.categorie);

            description =
                    itemView.findViewById(R.id.description);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            ViewHolder holder,
            int position
    ) {

        Depense d = liste.get(position);

        holder.montant.setText(
                String.valueOf(d.getMontant())
        );

        holder.description.setText(
                d.getDescription()
        );

        int categorieId =
                d.getCategorie_id();

        String nomCategorie = "Autres";

        for (Categorie c : categories) {

            if (c.getId() == categorieId) {
                nomCategorie = c.getNom();
                break;
            }
        }

        holder.categorie.setText(nomCategorie);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }
}