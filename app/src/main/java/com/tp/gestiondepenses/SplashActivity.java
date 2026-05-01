package com.tp.gestiondepenses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashActivity extends AppCompatActivity {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        executor.execute(() -> {

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());

            User admin = db.userDao().getUserByUsername("admin");

            if (admin == null) {
                User user = new User();
                user.username = "admin";
                user.password = "1234";
                db.userDao().insert(user);
            }

            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

            runOnUiThread(() -> {

                Intent i = isLoggedIn
                        ? new Intent(this, HomeActivity.class)
                        : new Intent(this, LoginActivity.class);

                startActivity(i);
                finish();

            });
        });
    }
}