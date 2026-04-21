package com.tp.gestiondepenses;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReclyclerViewDepense extends RecyclerView.ViewHolder {

    TextView CategorieView , MontantView , MPaiementView;


    public ReclyclerViewDepense(@NonNull View itemView) {
        super(itemView);
        CategorieView = itemView.findViewById(R.id.CategorieDepense);
        MontantView = itemView.findViewById(R.id.montantDepense);
        MPaiementView = itemView.findViewById(R.id.MoyenPaiementDepense);
    }


}
