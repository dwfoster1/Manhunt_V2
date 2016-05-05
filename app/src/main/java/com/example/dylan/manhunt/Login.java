package com.example.dylan.manhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This class handles logging in and is what alerts backgroundworker to the login type.
 * @author Dylan Foster
 * @version 5/4/16
 */
public class Login extends Activity {
    /** Text boxes that allows players to input username and password upon login */
    EditText UsernameEt, PasswordEt;

    @Override
    /**
     * Built in method in android that builds necessary instance state and button presses
     * that fire off intents to begin their specified activity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    /**
     * This is what happens when login is pressed, and sends the data to backgroundworker to start
     * the correct type to trigger a call to the server
     * @param view - view that is being seen by the user
     */
    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        startActivity(new Intent(this, StartScreen.class));
    }

    /**
     * This handles the change to the register screen once the button is pressed
     * @param view - Changes view to the appropriate view screen
     */
    public void OpenReg(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
