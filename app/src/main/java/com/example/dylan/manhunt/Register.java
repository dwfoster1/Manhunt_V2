package com.example.dylan.manhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This class handles the registration activity
 * @author Dylan Foster
 * @version 5/4/16
 */
public class Register extends AppCompatActivity {
    /** the name, username, and password text boxes */
    EditText name_db, username_db, password_db;

    @Override
    /**
     * builtin method that handles setting up the screen and activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name_db = (EditText)findViewById(R.id.et_name);
        username_db = (EditText)findViewById(R.id.et_username);
        password_db = (EditText)findViewById(R.id.et_password);
    }

    /**
     * This is what is triggered when the button is pressed, and passes the type "register" to
     * backgroundworker to start server communication to the database.
     * @param view - What is going on, on the screen
     */
    public void OnReg(View view) {
        String str_name = name_db.getText().toString();
        String str_username = username_db.getText().toString();
        String str_password = password_db.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_username, str_password);
    }
}