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

public class CategorieActivity extends AppCompatActivity {

    EditText nom , icone , couleur;
    Switch switchDefaut;
    View view_couleur;
    Button btn;


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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),CategorieListActivity.class);

                i.putExtra("Nom", nom.getText().toString());
                i.putExtra("Icone", icone.getText().toString());
                i.putExtra("couleur", couleur.getText().toString());
                i.putExtra("switchDefaut", switchDefaut.getText().toString());

                startActivity(i);
            }

        });
    }

}