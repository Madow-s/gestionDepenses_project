package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense_list);

        ls = findViewById(R.id.lst);

        ls.setLayoutManager(
                new LinearLayoutManager(this)
        );

        new Thread(() -> {

            AppDatabase db =
                    AppDatabase.getInstance(
                            getApplicationContext()
                    );

            liste =
                    db.depenseDao().getAll();

            List<Categorie> categories =
                    db.categorieDao().getAll();

            runOnUiThread(() -> {

                adapter =
                        new DepenseAdapter(
                                liste,
                                categories
                        );

                ls.setAdapter(adapter);

            });

        }).start();
    }
}