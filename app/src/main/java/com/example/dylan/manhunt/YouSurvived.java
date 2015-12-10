package com.example.dylan.manhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class displays that you survived. This might be updated with a list of other players who
 * also survived with you in that particular game.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class YouSurvived extends Activity {

    /** Back button that takes you to StartScreen */
    Button back;

    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_survived);

        back=(Button)findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StartScreen.class);
                startActivity(i);
            }
        });
    }
}
