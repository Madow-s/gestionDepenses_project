package com.tp.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {

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
                Intent i = new Intent(register.this, MainActivity.class);
                startActivity(i);
            }
        });






    }
}