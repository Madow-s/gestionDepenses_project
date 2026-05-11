package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Budget;

public class BudgetActivity extends AppCompatActivity {

    EditText montant, periode, mois, annee;

    Button save;

    Budget budgetExistant = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        montant =
                findViewById(R.id.montantBudget);

        periode =
                findViewById(R.id.periodeBudget);

        mois =
                findViewById(R.id.moisBudget);

        annee =
                findViewById(R.id.anneeBudget);

        save =
                findViewById(R.id.buttonSaveBudget);

        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            budgetExistant =
                    db.budgetDao().getBudget();

            runOnUiThread(() -> {

                // si un budget existe déjà
                if (budgetExistant != null) {

                    montant.setText(
                            String.valueOf(
                                    budgetExistant.getMontant_plafond()
                            )
                    );

                    periode.setText(
                            budgetExistant.getPeriode()
                    );

                    mois.setText(
                            String.valueOf(
                                    budgetExistant.getMois()
                            )
                    );

                    annee.setText(
                            String.valueOf(
                                    budgetExistant.getAnnee()
                            )
                    );
                }

            });

        }).start();

        save.setOnClickListener(v -> {

            double montantValue =
                    Double.parseDouble(
                            montant.getText().toString()
                    );

            String periodeValue =
                    periode.getText().toString();

            int moisValue =
                    Integer.parseInt(
                            mois.getText().toString()
                    );

            int anneeValue =
                    Integer.parseInt(
                            annee.getText().toString()
                    );

            new Thread(() -> {

                AppDatabase db =
                        AppDatabase.getInstance(
                                getApplicationContext()
                        );

                // si budget existe -> modifier
                if (budgetExistant != null) {

                    budgetExistant.setMontant_plafond(
                            montantValue
                    );

                    budgetExistant.setPeriode(
                            periodeValue
                    );

                    budgetExistant.setMois(
                            moisValue
                    );

                    budgetExistant.setAnnee(
                            anneeValue
                    );

                    db.budgetDao().update(
                            budgetExistant
                    );

                }

                // sinon créer
                else {

                    Budget nouveauBudget =
                            new Budget();

                    nouveauBudget.setMontant_plafond(
                            montantValue
                    );

                    nouveauBudget.setPeriode(
                            periodeValue
                    );

                    nouveauBudget.setMois(
                            moisValue
                    );

                    nouveauBudget.setAnnee(
                            anneeValue
                    );

                    db.budgetDao().insert(
                            nouveauBudget
                    );

                }

                runOnUiThread(() -> {
                    Intent intent = new Intent(BudgetActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                });

            }).start();

        });

    }
}