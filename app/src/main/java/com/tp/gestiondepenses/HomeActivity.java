package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Budget;

public class HomeActivity extends AppCompatActivity {

    Button btnDepense, btnRevenue;
    TextView soldeText, budgetText, depenseText, resteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.content_home);

        // INIT VIEWS
        btnDepense = findViewById(R.id.btnDepense);
        btnRevenue = findViewById(R.id.btnRevenue);

        soldeText = findViewById(R.id.soldeText);
        budgetText = findViewById(R.id.budgetText);
        depenseText = findViewById(R.id.depenseText);
        resteText = findViewById(R.id.resteText);

        // NAVIGATION
        btnDepense.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DepenseListActivity.class);
            startActivity(intent);
        });

        btnRevenue.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RevenuListActivity.class);
            startActivity(intent);
        });

        chargerDonnees();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chargerDonnees();
    }

    // 🔥 TOUT CENTRALISÉ ICI
    private void chargerDonnees() {

        new Thread(() -> {

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());

            Budget budget = db.budgetDao().getBudget();

            double totalDepenses = db.depenseDao().getTotalDepenses();
            double totalRevenus = db.revenuDao().getTotalRevenus();

            final double[] budgetValue = {0};

            if (budget != null) {
                budgetValue[0] = budget.getMontant_plafond();
            }

            double solde = totalRevenus - totalDepenses;
            double reste = budgetValue[0] - totalDepenses;

            runOnUiThread(() -> {

                budgetText.setText("Budget : " + budgetValue[0] + " FCFA");
                depenseText.setText("Dépenses : " + totalDepenses + " FCFA");
                resteText.setText("Reste : " + reste + " FCFA");
                soldeText.setText("Solde : " + solde + " FCFA");

            });

        }).start();
    }
}