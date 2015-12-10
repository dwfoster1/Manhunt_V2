package com.example.dylan.manhunt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * This class displays the contents of StartScreen with a hardcoded list of players that will
 * be playing the game inside the "lobby".
 *
 * @author Dylan
 * @version 12/10/15
 */
public class StartScreen extends Activity {

    /** ListView that we will be seeing for players */
    private ListView lv;

    /** Buttons to go back or continue to next screen*/
    Button start, back;

    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout and
     * also incorporates an onClick listener that fires off the intent to start the next screen.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        lv = (ListView) findViewById(R.id.listView);

        // Instantiating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("DylanF");
        your_array_list.add("WilliamK");
        your_array_list.add("JohnB");
        your_array_list.add("CharlesM");
        your_array_list.add("EricaK");
        your_array_list.add("DanaB");
        your_array_list.add("MichaelK");
        your_array_list.add("TommyH");
        your_array_list.add("CliftonW");
        your_array_list.add("CharlesH");
        your_array_list.add("TylerA");
        your_array_list.add("TrevorG");
        your_array_list.add("PatrickL");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);

        start=(Button)findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SetupGame.class);
                startActivity(i);
            }
        });

        back=(Button)findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateGame.class);
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
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
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
