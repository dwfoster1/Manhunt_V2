package com.example.dylan.manhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Activity that handles button presses for the Login Screen screen of Manhunt.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class LoginScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    /**
     * I don't need a listener for the buttons since the onClick declared in text iml and this
     * automatically prepares the event of pressing a button to go to CreateGame screen.
     *
     * @param view - view that is being clicked
     */
    public void onClick(View view) {
        Intent i = new Intent(this, CreateGame.class);
        startActivity(i);
    }

    @Override
    /**
     * Default android method that displays information on an action bar if it is present.
     *
     * @param menu - menu that appears as an action bar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
        return true;
    }

    @Override
    /**
     * Handle action bar item clicks here. The action bar will automatically handle
     * clicks on the Home/Up button, so long as you specify a parent activity in
     * AndroidManifest.xml.
     *
     * @param item - Items present inside action bar
     * @return item - item that was selected
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}