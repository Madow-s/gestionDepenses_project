package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.User;

public class MainActivity extends AppCompatActivity {


    EditText username, password;
    Button btnLogin , btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(view -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) return;

            new Thread(() -> {

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                User u = db.userDao().login(user, pass);

                runOnUiThread(() -> {
                    if (u != null) {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    } else {
                        username.setError("Erreur");
                        password.setError("Erreur");
                    }
                });

            }).start();
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);

            }
        });

    }
}