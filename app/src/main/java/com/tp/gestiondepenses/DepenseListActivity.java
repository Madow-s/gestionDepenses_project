package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class DepenseListActivity extends AppCompatActivity {


    RecyclerView ls;
    String categorie,moyentPaiement,description,date,montant,rubrique;
    HashMap<String,String> map;
    Params p =new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_depense_list);

        ls = findViewById(R.id.lst);

        Bundle extras=getIntent().getExtras();
        if (extras != null)
        {
            montant= extras.getString("montant");
            moyentPaiement= extras.getString("moyentPaiement");
            description= extras.getString("description");
            date= extras.getString("date");
            rubrique= extras.getString("rubrique");
            categorie= extras.getString("categorie");

            map= new HashMap<String,String>();
            map.put("montant",categorie);
            map.put("moyentPaiement",categorie);
            map.put("description",categorie);
            map.put("date",categorie);
            map.put("rubrique",categorie);
            map.put("categorie",categorie);
            p.values.add(map);

        }

        SimpleAdapter adapter = new SimpleAdapter(DepenseListActivity.this,p.values,R.layout.item,
                new String[]{"montant","moyentPaiement","description","date","rubrique","categorie"},
                new int[]{R.id.montant,R.id.moyenPaiement,R.id.description,R.id.dateDepense,R.id.rubriqueList,R.id.categorieList}
                );
        ls.setAdapter(adapter);




    }
}