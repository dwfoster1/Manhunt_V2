package com.example.dylan.manhunt;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Displays the SetupGame screen and creates customizable game features for players to enhance
 * their experience.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class SetupGame extends Activity {

    /** Spinners that will have drop downs with customizable options */
    private Spinner spinner1, spinner2, spinner3;

    /** Buttons that start the game or go to previous screen */
    Button start, back;

    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout and
     * also incorporates an onClick listener that fires off the intent to start the next screen.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        start=(Button)findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), YouAreBeingHunted.class);
                startActivity(i);
            }
        });

        addItemsOnSpinner3();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    /**
     * Add items into spinners dynamically.
     */
    public void addItemsOnSpinner3() {
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        List<String> list = new ArrayList<String>();
        list.add("1 Hunter");
        list.add("2 Hunters");
        list.add("3 Hunters");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
    }

    /**
     * Testing method to display contents of what was selected.
     */
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    /**
     * Gets the selected dropdown list value and displays a toast once pressed.
     */
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View v) {

                Toast.makeText(SetupGame.this,
                        "OnClickListener : " +
                                "\nHiding Duration : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nGame Duration : " + String.valueOf(spinner2.getSelectedItem()) +
                                "\nNumber of Hunters : " + String.valueOf(spinner3.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_setup_game, menu);
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

