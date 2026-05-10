package com.tp.gestiondepenses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Categorie;
import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepenseActivity extends AppCompatActivity {


    EditText  moyentPaiement , description , montant , date ;
    Spinner categorieSpinner , rubrique;
    Button add , annuler;
    List<Categorie> categories = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);

        montant = findViewById(R.id.montant);
        moyentPaiement = findViewById(R.id.moyenPaiement);
        description = findViewById(R.id.description);
        date = findViewById(R.id.dateDepense);
        rubrique = findViewById(R.id.rubriqueList);
        add = findViewById(R.id.buttonSaveDepense);
        annuler = findViewById(R.id.button2);
        categorieSpinner = findViewById(R.id.categorieList);

        String[] rubriques = {
                "Maison",
                "Transport",
                "Santé",
                "Loisir",
                "Autre"
        };

        ArrayAdapter<String> rubriqueAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        rubriques
                );

        rubriqueAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        rubrique.setAdapter(rubriqueAdapter);

        add.setOnClickListener(v -> {

            double montantValue = 0;

            try {

                montantValue =
                        Double.parseDouble(
                                montant.getText().toString()
                        );

            } catch (Exception e) {

                montantValue = 0;
            }

            String moyen =
                    moyentPaiement.getText().toString();

            String desc =
                    description.getText().toString();

            String dateValue =
                    date.getText().toString();

            String rubriqueValue = "aucune";

            if (rubrique.getSelectedItem() != null) {
                rubriqueValue =
                        rubrique.getSelectedItem().toString();
            }

            // catégorie choisie dans le Spinner
            Categorie categorieChoisie =
                    (Categorie) categorieSpinner.getSelectedItem();

            if (categorieChoisie == null) {
                return;
            }
            // récupération de l'id
            int categorieId =
                    categorieChoisie.getId();

            Depense depense = new Depense(
                    montantValue,
                    moyen,
                    desc,
                    dateValue,
                    categorieId,
                    rubriqueValue
            );

            new Thread(() -> {

                AppDatabase db =
                        AppDatabase.getInstance(
                                getApplicationContext()
                        );

                db.depenseDao().insert(depense);

                runOnUiThread(() -> {

                    Intent i = new Intent(
                            DepenseActivity.this,
                            DepenseListActivity.class
                    );

                    startActivity(i);

                });

            }).start();

        });

        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            categories =
                    db.categorieDao().getAll();

            runOnUiThread(() -> {

                ArrayAdapter<Categorie> adapter =
                        new ArrayAdapter<>(
                                DepenseActivity.this,
                                android.R.layout.simple_spinner_item,
                                categories
                        );

                adapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                );

                categorieSpinner.setAdapter(adapter);

            });

        }).start();

    }



}
