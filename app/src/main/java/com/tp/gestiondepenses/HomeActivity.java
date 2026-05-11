package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.gestiondepenses.conf.database.AppDatabase;

public class HomeActivity extends AppCompatActivity {

    Button btnDepense, btnRevenue;
    TextView soldeText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.content_home);


        btnDepense = findViewById(R.id.btnDepense);
        btnRevenue = findViewById(R.id.btnRevenue);
        soldeText =
                findViewById(R.id.soldeText);

        btnDepense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DepenseListActivity.class);
                startActivity(intent);
            }
        });

        btnRevenue.setOnClickListener(v -> {

            Intent i = new Intent(
                    HomeActivity.this,
                    RevenuListActivity.class
            );

            startActivity(i);

        });


        // Calcul du solde
        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            double totalDepenses =
                    db.depenseDao().getTotalDepenses();

            double totalRevenus =
                    db.revenuDao().getTotalRevenus();

            double solde =
                    totalRevenus - totalDepenses;

            runOnUiThread(() -> {

                soldeText.setText(
                        "Solde : " + solde + " FCFA"
                );

            });

        }).start();
    }







}
