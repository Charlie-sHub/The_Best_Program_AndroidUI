package com.dam.reto2dam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import thebestprogramlogiclibrary.User;
import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;

public class ActivityRegister extends Activity implements View.OnClickListener {
    private Button btnSubmit;
    private Button btnCancel;
    private EditText fieldUsername;
    private EditText fieldPassword;
    private EditText fieldConfirmPassword;
    private EditText fieldEmail;
    private EditText fieldFullName;
    private User user;
    private ApplicationLogicImplementation appLogic;

    public void setAppLogic(ApplicationLogicImplementation appLogic) {
        this.appLogic = appLogic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        btnSubmit.setEnabled(false);

        // how to add Listeners to the fields?
    }

    private void findViews() {
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        fieldUsername = findViewById(R.id.fieldUsername);
        fieldPassword = findViewById(R.id.fieldPassword);
        fieldConfirmPassword = findViewById(R.id.fieldConfirmPassword);
        fieldEmail = findViewById(R.id.fieldEmail);
        fieldFullName = findViewById(R.id.fieldFullName);
    }

    /**
     * This method dictates what happens when the buttons are pressed
     *
     * @param v
     */
    public void onClick(View v) {
        try {
            if (v.getId() == btnSubmit.getId()) {
                user = new User();
                user.setEmail(String.valueOf(fieldEmail.getText()));
                user.setFullName(String.valueOf(fieldFullName.getText()));
                user.setLogin(String.valueOf(fieldUsername.getText()));
                user.setPassword(String.valueOf(fieldPassword.getText()));
                if (appLogic.registerUser(user)) {
                    Intent mainActivity = new Intent(activity_aplicationmainmenu.xml);
                    //open the intent somehow
                }
            } else {
                //How to go back to the previous activity (login)
            }
        } catch (Exception e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.show();
        }

    }
}
