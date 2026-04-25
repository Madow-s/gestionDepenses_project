package com.tp.gestiondepenses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepenseActivity extends AppCompatActivity {


    EditText  moyentPaiement , description , montant , date ;
    Spinner categorie , rubrique;
    Button add , annuler;

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
        categorie = findViewById(R.id.categorieList);
        add = findViewById(R.id.buttonSaveDepense);
        annuler = findViewById(R.id.button2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),DepenseListActivity.class);

                i.putExtra("montant", montant.getText().toString());
                i.putExtra("moyentPaiement", moyentPaiement.getText().toString());
                i.putExtra("description", description.getText().toString());
                i.putExtra("date", date.getText().toString());
                i.putExtra("rubrique", rubrique.getSelectedItem().toString());
                i.putExtra("categorie", categorie.getSelectedItem().toString());

                startActivity(i);


            }
        });

    }



}
