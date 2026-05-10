package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Categorie;

public class CategorieActivity extends AppCompatActivity {

    EditText nom , icone , couleur;
    Switch switchDefaut;
    View view_couleur;
    Button btn , annuler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorie);

        nom = findViewById(R.id.CategorieName);
        icone = findViewById(R.id.CategorieIcon);
        view_couleur = findViewById(R.id.viewCouleur);
        couleur = findViewById(R.id.CategorieCouleur);
        switchDefaut = findViewById(R.id.switchDefaut);
        btn = findViewById(R.id.CategorieButton);
        annuler = findViewById(R.id.annuler);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomValue = nom.getText().toString();
                String iconeValue = icone.getText().toString();
                String couleurValue = couleur.getText().toString();

                boolean estDefaut =
                        switchDefaut.isChecked();

                Categorie categorie = new Categorie(
                        nomValue,
                        iconeValue,
                        couleurValue,
                        estDefaut
                );

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        AppDatabase db =
                                AppDatabase.getInstance(
                                        getApplicationContext()
                                );

                        db.categorieDao().insert(categorie);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(
                                        CategorieActivity.this,
                                        CategorieListActivity.class
                                );

                                startActivity(i);
                            }
                        });
                    }
                }).start();
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CategorieActivity.this, CategorieListActivity.class);
                startActivity(i);

            }
        });
    }

}