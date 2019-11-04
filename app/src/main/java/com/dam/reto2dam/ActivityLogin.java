package com.dam.reto2dam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class    ActivityLogin extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtUsername;
    private TextView txtPassword;
    private EditText fieldUsername;
    private EditText fieldPassword;
    private Button btnLogin;
    private TextView txtNoAccount;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onBtnLoginPress(){

    }

    public void onCheckedHappyMode() {

    }

    public void onBtnRegisterPress() {

    }
}
