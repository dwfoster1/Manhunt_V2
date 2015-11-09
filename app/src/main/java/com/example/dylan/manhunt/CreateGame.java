package com.example.dylan.manhunt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CreateGame extends Activity {

    Button create;
    Button rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        create=(Button)findViewById(R.id.button_create);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), StartScreen.class);
                startActivity(i);
            }
        });

        rules=(Button)findViewById(R.id.button_rules);
        rules.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Rules.class);
                startActivity(i);
            }
        });
    }

    //public void onClick(View view) {
        //Intent i = new Intent(this, StartScreen.class);

        //final EditText screenAInput = (EditText) findViewById(R.id.screenAInput);
        //String userMessage = screenAInput.getText().toString();
        //key value pair to pass the message to screenB
        //i.putExtra("inputMessage", userMessage);

        //startActivity(i);
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
        return true;
    }

    @Override
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
