package com.example.dylan.manhunt;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.app.Activity;

/**
 * This class displays the contents of YouAreBeingHunted if a player is chosen to be a survivor.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class YouAreBeingHunted extends Activity {

    /** My particular layout for this activity */
    ViewGroup dylansLayout;

    /** Duration of the timer countdown */
    MyCount timerCount;

    /** Value of the timer */
    private TextView timerValue;

    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout and
     * also sets a timer for the countdown
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are_being_hunted);


        dylansLayout = (ViewGroup) findViewById(R.id.dylansLayout);

        setContentView(R.layout.activity_you_are_being_hunted);
        timerValue = (TextView) findViewById(R.id.timerValue);


        timerCount = new MyCount(11 * 1000, 1000);
        timerCount.start();
    }

    /**
     * My timer and the corresponding code.
     */
    public class MyCount extends CountDownTimer {
        /**
         * What is counting down and in what interval.
         *
         * @param millisInFuture - How long it will wait
         * @param countDownInterval - How long it will count for
         */
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        /**
         * Displays a small box animation once timer has completed
         */
        public void onFinish() {
            timerValue.setText("-BEGIN HUNT-");
            timerValue.setTextSize(50);

            TranslateAnimation trans = new TranslateAnimation(0, 0, 0, 1600);
            trans.setDuration(3000);
            trans.setInterpolator(new AccelerateInterpolator(1.0f));
            timerValue.startAnimation(trans);
            onAnimationEnd();
        }

        /**
         * Fires intent to go to next screen once the animation ends.
         */
        public void onAnimationEnd() {
            Intent intent = new Intent(YouAreBeingHunted.this, MapsActivity.class);
            startActivity(intent);
        }

        @Override
        /**
         * Updates the timer countdown
         *
         * @param millisUntilFinished - the seconds until it completes counting down
         */
        public void onTick(long millisUntilFinished) {
            timerValue.setText("Time Left: " + millisUntilFinished / 1000);
        }

    }

    @Override
    /**
     * Default android method that displays information on an action bar if it is present.
     *
     * @param menu - menu that appears as an action bar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_are_being_hunted, menu);
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
