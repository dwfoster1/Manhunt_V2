package com.example.dylan.manhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Activity that handles button presses for the CreateGame screen of Manhunt.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class CreateGame extends Activity {

    /** Buttons that will be on the screen to open corresponding activity */
    Button create, rules;

    /**
     * Built in method in android that builds necessary instance state and button presses
     * that fire off intents to begin their specified activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        create=(Button)findViewById(R.id.button_create);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Fires an intent to the StartScreen class once button is pressed.
             *
             * @param View v - view that is being clicked
             */
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), StartScreen.class);
                startActivity(i);
            }
        });

        rules=(Button)findViewById(R.id.button_rules);
        rules.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Fires an intent to the Rules class once button is pressed.
             *
             * @param v - view that is being clicked
             */
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Rules.class);
                startActivity(i);
            }
        });
    }

    @Override
    /**
     * Default android method that displays information on an action bar if it is present.
     *
     * @param menu - menu that appears as an action bar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
