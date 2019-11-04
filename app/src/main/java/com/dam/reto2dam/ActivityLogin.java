package com.dam.reto2dam;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import thebestprogramlogiclibrary.User;
import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;


public class    ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private TextView txtTitle;
    private TextView txtUsername;
    private TextView txtPassword;
    private EditText fieldUsername;
    private EditText fieldPassword;
    private Button btnLogin;
    private TextView txtNoAccount;
    private Button btnRegister;
    private ArrayList<EditText> textFields;
    private User user;
    private ApplicationLogicImplementation appLogic;

    public void setAppLogic(ApplicationLogicImplementation appLogic) {
        this.appLogic = appLogic;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        textFields = new ArrayList<EditText>();

        addFieldsToArray();
    }



    private void findViews() {
        txtTitle = findViewById(R.id.txtTitle);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        fieldUsername = findViewById(R.id.fieldUsername);
        fieldPassword = findViewById(R.id.fieldPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtNoAccount = findViewById(R.id.txtNoAccount);
        btnRegister = findViewById(R.id.btnRegister);
    }
    private void addFieldsToArray() {
        textFields.add(fieldUsername);
        textFields.add(fieldPassword);

    }
    public void onBtnLoginPress(){

    }

    public void onCheckedHappyMode() {

    }

    public void onBtnRegisterPress() {

    }

    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == btnLogin.getId()){
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
                    client.setAction("LOGIN");//Cambia a setAction() el mensaje
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

                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("There are empty fields");
                    alert.show();
                }
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
}
