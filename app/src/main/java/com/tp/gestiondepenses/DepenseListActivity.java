package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.List;

public class DepenseListActivity extends AppCompatActivity {

    RecyclerView ls;
    List<Depense> liste = new ArrayList<>();
    DepenseAdapter adapter;

    Button boutonAjout, btnCat;
    TextView totalDepenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense_list);

        ls = findViewById(R.id.lst);
        boutonAjout = findViewById(R.id.btnAjoutDep);
        btnCat = findViewById(R.id.btnCat);
        totalDepenses = findViewById(R.id.totalDepenses);

        ls.setLayoutManager(
                new LinearLayoutManager(this)
        );

        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            liste = db.depenseDao().getAll();

            List<Categorie> categories =
                    db.categorieDao().getAll();

            double total = db.depenseDao().getTotalDepenses();

            runOnUiThread(() -> {

                adapter =
                        new DepenseAdapter(
                                liste,
                                categories
                        );

                ls.setAdapter(adapter);

                totalDepenses.setText(
                        "Total dépenses : " + total + " FCFA"
                );

            });

        }).start();


        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DepenseListActivity.this, DepenseActivity.class);
                startActivity(i);

            }
        });

        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DepenseListActivity.this, CategorieActivity.class);
                startActivity(i);

            }
        });
    }



}