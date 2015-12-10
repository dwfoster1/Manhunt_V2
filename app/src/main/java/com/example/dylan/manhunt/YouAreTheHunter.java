package com.example.dylan.manhunt;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;
import android.app.Activity;

/**
 * This class displays the contents of YouAreBeingHunter if a player is chosen to be a hunter.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class YouAreTheHunter extends Activity {

    /** The timer text view that will be updating */
    TextView timerTextView;

    /** What time it will start at*/
    long startTime = 0;

    /** runs without a timer by re-posting this handler at the end of the runnable */
    Handler timerHandler = new Handler();

    /** runs without a timer by re-posting this handler at the end of the runnable */
    Runnable timerRunnable = new Runnable() {

        @Override
        /**
         * the timer that runs and updates on the screen timer text view.
         */
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout and
     * also sets a timer for the countdown
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are_the_hunter);

        timerTextView = (TextView) findViewById(R.id.timerValue);

        Button b = (Button) findViewById(R.id.button);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * Listener that takes us to the appropriate screen.
             *
             * @param view - the change in views when going to new activity
             */
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });
    }

    @Override
    /**
     * Handles when timer is paused on the thread that was created for it.
     */
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }

    @Override
    /**
     * Default android method that displays information on an action bar if it is present.
     *
     * @param menu - menu that appears as an action bar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_are_the_hunter, menu);
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
