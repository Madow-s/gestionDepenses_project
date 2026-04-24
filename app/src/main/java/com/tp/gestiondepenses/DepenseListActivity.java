package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepenseListActivity extends AppCompatActivity {

    RecyclerView ls;
    List<Depense> liste = new ArrayList<>();
    DepenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense_list);

        ls = findViewById(R.id.lst);
        ls.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String montantStr = extras.getString("montant");

            double montant = 0;
            if (montantStr != null && !montantStr.isEmpty()) {
                montant = Double.parseDouble(montantStr);
            }

            String moyentPaiement = extras.getString("moyentPaiement");
            String description = extras.getString("description");
            String date = extras.getString("date");

            String RubriqueStr = extras.getString("rubrique");
            int rubrique = 0;
            if (RubriqueStr != null && !RubriqueStr.isEmpty()) {
                rubrique = Integer.parseInt(RubriqueStr);
            }

            String CategorieStr = extras.getString("categorie");
            int categorie = 0;
            if (CategorieStr != null && !CategorieStr.isEmpty()) {
                categorie = Integer.parseInt(CategorieStr);
            }

            String DateStr = extras.getString("date");
            long date = 0;
            if (DateStr != null && !DateStr.isEmpty()) {
                date = Long.parseLong(DateStr);
            }

            Depense d = new Depense(montant, moyentPaiement, description, date, rubrique, categorie);
            liste.add(d);
        }

        adapter = new DepenseAdapter(liste);
        ls.setAdapter(adapter);
    }
}