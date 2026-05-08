package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.tp.gestiondepenses.conf.database.AppDatabase;
import com.tp.gestiondepenses.conf.entity.User;

public class Register extends AppCompatActivity {

    EditText edtFullname, edtEmailAdress, edtPassword, edtDOB, edtPhoneNumber, edtBio;
    Button btnRegsiterReg , btnLoginReg ;
    TextView txtDisplayInfoReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        edtFullname = findViewById(R.id.edtFullnameReg);
        edtEmailAdress = findViewById(R.id.edtEmailAdressReg);
        edtPassword = findViewById(R.id.edtPasswordReg);
        edtDOB = findViewById(R.id.edtDOBReg);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumberReg);
        edtBio = findViewById(R.id.edtBioReg);

        btnRegsiterReg = findViewById(R.id.btnRegisterReg);
        btnLoginReg = findViewById(R.id.btnLoginReg);

        txtDisplayInfoReg = findViewById(R.id.txtDisplayInfoReg);


        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnRegsiterReg.setOnClickListener(view -> {

            String strUsername = edtFullname.getText().toString().trim();
            String strEmail = edtEmailAdress.getText().toString().trim();
            String strPassword = edtPassword.getText().toString().trim();
            String strDOB = edtDOB.getText().toString().trim();
            String strPhoneNumber = edtPhoneNumber.getText().toString().trim();
            String strBio = edtBio.getText().toString().trim();

            if (strUsername.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty()
                    || strDOB.isEmpty() || strPhoneNumber.isEmpty() || strBio.isEmpty()) {

                txtDisplayInfoReg.setText("Tous les champs sont obligatoires");
                return;
            }

            new Thread(() -> {

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());

                User user = new User();
                user.setUsername(strUsername);
                user.setEmail(strEmail);
                user.setPassword(strPassword);
                user.setDateOB(strDOB);
                user.setPhoneNumber(strPhoneNumber);
                user.setBio(strBio);

                db.userDao().insert(user);

                runOnUiThread(() -> {
                    txtDisplayInfoReg.setText("Inscription réussie");

                    // Redirection vers login
                    startActivity(new Intent(Register.this, MainActivity.class));
                    finish();
                });

            }).start();
        });




    }
}