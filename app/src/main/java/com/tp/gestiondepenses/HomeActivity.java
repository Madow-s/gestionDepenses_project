package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    Button btnDepense, btnCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.content_home);


        btnDepense = findViewById(R.id.btnDepense);

        btnDepense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DepenseListActivity.class);
                startActivity(intent);
            }
        });

        btnCategorie.setOnClickListener(v -> {

            Intent i = new Intent(
                    HomeActivity.this,
                    CategorieListActivity.class
            );

            startActivity(i);

        });
    }






}
