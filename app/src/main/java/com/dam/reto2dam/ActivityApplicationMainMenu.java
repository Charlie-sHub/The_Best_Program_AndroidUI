package com.dam.reto2dam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import thebestprogramlogiclibrary.User;

/**
 * The activity for the main view of the app. For the moment
 * just shows the full name of the logged user and makes a logout.
 */
public class ActivityApplicationMainMenu extends Activity implements View.OnClickListener {

    private TextView txtUserName;
    private Button btnLogout;

    private Bundle extrasBundle;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicationmainmenu);

        findViews();

        btnLogout.setOnClickListener(this);
        extrasBundle = new Bundle();
        extrasBundle = getIntent().getExtras();
        user = (User) extrasBundle.get("USER");

        txtUserName.setText(user.getFullName());
    }

    private void findViews() {

        btnLogout = findViewById(R.id.btnLogout);
        txtUserName = findViewById(R.id.txtUserName);
    }

    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == btnLogout.getId()) {
                Intent loginActivityIntent = new Intent(this, ActivityLogin.class);
                deleteUser();
                loginActivityIntent.putExtra("user", user);
                startActivity(loginActivityIntent);
            }


        } catch (Exception e) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.show();
        }
    }

    /**
     * The user stored on the memory is equal to null.
     */
    private void deleteUser() {

        this.user = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
