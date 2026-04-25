package com.tp.gestiondepenses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Attendre 2 secondes (2000 millisecondes)
        new Handler().postDelayed(() -> {

            // Vérifier si l'utilisateur est déjà connecté
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

            if (isLoggedIn) {
                // Déjà connecté → aller au Dashboard
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // Pas connecté → aller à la connexion
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }

            finish(); // fermer le SplashScreen pour ne pas y revenir

        }, 2000);
    }
}