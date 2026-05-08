package com.tp.gestiondepenses;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.entity.Categorie;
import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.ArrayList;
import java.util.List;

public class CategorieListActivity extends AppCompatActivity {



    RecyclerView ls;
    List<Categorie> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorie_list);

        ls = findViewById(R.id.ls);
        ls.setLayoutManager(new LinearLayoutManager(this));


        Bundle extras=getIntent().getExtras();
        if (extras != null){

            String nomCategorie = extras.getString("nomCategorie");
            String iconeCategorie = extras.getString("iconeCategorie");
            String couleur = extras.getString("couleur");
            boolean est_defaut = extras.getBoolean("est_defaut");

            Categorie c = new Categorie(nomCategorie, iconeCategorie, couleur, est_defaut );
            liste.add(c);

        }



    }
}