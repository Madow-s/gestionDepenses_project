package com.tp.gestiondepenses;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.entity.Depense;

import java.util.ArrayList;
import java.util.List;

public class DepenseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_depense);

        RecyclerView recyclerviewdepense = findViewById(R.id.listDepense);

        List<Depense> items = new ArrayList<Depense>();
        items.add(new Depense());

        recyclerviewdepense.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewdepense.setAdapter(new AdapterDepense(getApplicationContext(),items));




    }
}
