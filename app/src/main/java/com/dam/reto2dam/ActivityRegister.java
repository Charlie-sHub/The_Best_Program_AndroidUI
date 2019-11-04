package com.dam.reto2dam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityRegister extends AppCompatActivity {
    private Button btnSubmit;
    private Button btnCancel;
    private EditText fieldUsername;
    private EditText fieldPassword;
    private EditText fieldConfirmPassword;
    private EditText fieldEmail;
    private EditText fieldFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        fieldUsername = findViewById(R.id.fieldUsername);
        fieldPassword = findViewById(R.id.fieldPassword);
        fieldConfirmPassword = findViewById(R.id.fieldConfirmPassword);
        fieldEmail = findViewById(R.id.fieldEmail);
        fieldFullName = findViewById(R.id.fieldFullName);

    }
}
