package com.tp.gestiondepenses;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.database.AppDatabase;
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

        ls.setLayoutManager(
                new LinearLayoutManager(this)
        );

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase db =
                        AppDatabase.getInstance(
                                getApplicationContext()
                        );

                liste = db.categorieDao().getAll();

                for (Categorie c : liste) {
                    android.util.Log.d("DB_CATEGORIE",
                            "Nom: " + c.getNom()
                                    + " | Couleur: " + c.getCouleur()
                                    + " | Icone: " + c.getIcone());
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CategorieAdapter adapter =
                                new CategorieAdapter(liste);

                        ls.setAdapter(adapter);
                    }
                });
            }
        }).start();


    }
}