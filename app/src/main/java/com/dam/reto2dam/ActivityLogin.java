package com.dam.reto2dam;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

import thebestprogramlogiclibrary.User;
import thebestprogramlogiclibrary.logic.ApplicationLogicFactory;
import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;


public class    ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText fieldUsername;
    private EditText fieldPassword;
    private Button btnLogin;
    private Button btnRegister;
    private ArrayList<EditText> textFields;
    private User user;
    private ApplicationLogicImplementation appLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        try {
            appLogic = ApplicationLogicFactory.getAppLogicImpl();
        }catch (IOException e){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.show();
        }
        String host = ResourceBundle.getBundle("thebestprogramdesktop.connection").getString("host");
        int port = Integer.parseInt(ResourceBundle.getBundle("thebestprogramdesktop.connection").getString("port"));
        appLogic.setHost(host);
        appLogic.setPort(port);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        textFields = new ArrayList<>();

        addFieldsToArray();
    }



    private void findViews() {
        fieldUsername = findViewById(R.id.fieldUsername);
        fieldPassword = findViewById(R.id.fieldPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }
    private void addFieldsToArray() {
        textFields.add(fieldUsername);
        textFields.add(fieldPassword);

    }

    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == btnLogin.getId()){
                onBtnLoginPress();
            }else if (v.getId() == btnRegister.getId()){
                onBtnRegisterPress();
            }

        }catch (Exception e){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.show();
        }
    }
    private void userLogin(){
        user = new User();
        user.setLogin(String.valueOf(fieldUsername.getText()));
        user.setPassword(String.valueOf(fieldPassword.getText()));
    }

    /**
     * This method will be on charge of doing the login.
     * Will be initialised when the client click the login method
     * Firstly will verify if all the fields are filled, then would try to connect and verify the data
     */
    public void onBtnLoginPress(){
        boolean filledFields = true;
        for (EditText field : textFields) {
            if (field.getText().length() == 0) {
                filledFields = false;
                break;
            }
        }
        if(filledFields){
            userLogin();
            ClientThread client = new ClientThread();
            client.setAction("LOGIN");
            client.setUser(user);
            client.setAppLogic(appLogic);
            client.start();
            try {
                client.join();
            }catch (Exception e){
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage(e.getMessage());
                alert.show();
            }
            if (client.getMessage().getContent() instanceof User){
                user = (User) client.getMessage().getContent();
                Intent androidMainActivityIntent = new Intent(this, ActivityApplicationMainMenu.class);
                androidMainActivityIntent.putExtra("USER", user);
                startActivity(androidMainActivityIntent);
            }else {
                Exception e = (Exception) client.getMessage().getContent();
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage(e.getMessage());
                alert.show();
            }

        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("There are empty fields");
            alert.show();
        }
    }

    /**
     * This function will be on charge of throwing the user to the register windows
     * Will be initialised when the user clicks on the register button
     */
    public void onBtnRegisterPress() {
        Intent registerIntent = new Intent(this, ActivityRegister.class);
        registerIntent.putExtra("APPLOGIC", (Serializable) appLogic);
        startActivity(registerIntent);
    }
}
