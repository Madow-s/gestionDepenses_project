package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Remplis tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            btnLogin.setEnabled(false);

            executor.execute(() -> {

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                User u = db.userDao().login(user, pass);

                runOnUiThread(() -> {

                    btnLogin.setEnabled(true);

                    if (u != null) {

                        getSharedPreferences("user_prefs", MODE_PRIVATE)
                                .edit()
                                .putBoolean("is_logged_in", true)
                                .apply();

                        startActivity(new Intent(this, HomeActivity.class));
                        finish();

                    } else {
                        Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }
}