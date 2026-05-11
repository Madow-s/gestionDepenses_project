package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Revenu;

public class RevenuActivity extends AppCompatActivity {

    EditText source, montant, date, description;
    Button add, annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        source = findViewById(R.id.source);
        montant = findViewById(R.id.montantRevenu);
        date = findViewById(R.id.dateRevenu);
        description = findViewById(R.id.descriptionRevenu);

        add = findViewById(R.id.buttonSaveRevenu);
        annuler = findViewById(R.id.buttonAnnulerRevenu);

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

            String sourceValue =
                    source.getText().toString();

            String dateValue =
                    date.getText().toString();

            String descriptionValue =
                    description.getText().toString();

            Revenu revenu = new Revenu();

            revenu.setSource(sourceValue);
            revenu.setMontant(montantValue);
            revenu.setDescription(descriptionValue);

            revenu.setDate(
                    System.currentTimeMillis()
            );

            new Thread(() -> {

                AppDatabase db =
                        AppDatabase.getInstance(
                                getApplicationContext()
                        );

                db.revenuDao().insert(revenu);

                runOnUiThread(() -> {

                    Intent i = new Intent(
                            RevenuActivity.this,
                            RevenuListActivity.class
                    );

                    startActivity(i);

                });

            }).start();

        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RevenuActivity.this, RevenuListActivity.class);
                startActivity(i);

            }
        });

    }
}