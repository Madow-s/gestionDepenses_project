package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.Revenu;

import java.util.ArrayList;
import java.util.List;

public class RevenuListActivity
        extends AppCompatActivity {

    RecyclerView ls;

    List<Revenu> liste =
            new ArrayList<>();

    RevenuAdapter adapter;
    Button btnAjoutRev;

    TextView totalRevenus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_list);

        ls = findViewById(R.id.lstRevenu);
        btnAjoutRev = findViewById(R.id.btnAjoutRev);
        totalRevenus = findViewById(R.id.totalRevenus);

        ls.setLayoutManager(
                new LinearLayoutManager(this)
        );

        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            liste =
                    db.revenuDao().getAll();
            double total = db.revenuDao().getTotalRevenus();


            runOnUiThread(() -> {

                adapter =
                        new RevenuAdapter(liste);

                ls.setAdapter(adapter);

                totalRevenus.setText(
                        "Total revenus : " + total + " FCFA"
                );

            });

        }).start();


        btnAjoutRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RevenuListActivity.this,RevenuActivity.class);
                startActivity(i);

            }
        });



    }
}