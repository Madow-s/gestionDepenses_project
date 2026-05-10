package com.tp.gestiondepenses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Revenu;

import java.util.List;

public class RevenuAdapter
        extends RecyclerView.Adapter<RevenuAdapter.ViewHolder> {

    List<Revenu> liste;

    public RevenuAdapter(List<Revenu> liste) {
        this.liste = liste;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView source, montant, description;

        public ViewHolder(View itemView) {
            super(itemView);

            source =
                    itemView.findViewById(R.id.source);

            montant =
                    itemView.findViewById(R.id.montant);

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
                        .inflate(
                                R.layout.item_revenu,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            ViewHolder holder,
            int position
    ) {

        Revenu r = liste.get(position);

        holder.source.setText(
                r.getSource()
        );

        holder.montant.setText(
                String.valueOf(r.getMontant())
        );

        holder.description.setText(
                r.getDescription()
        );
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }
}