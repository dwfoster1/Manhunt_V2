package com.example.dylan.manhunt;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;
import android.app.Activity;

public class YouAreBeingHunted extends Activity {

    ViewGroup dylansLayout;
    MyCount timerCount;
    private TextView timerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are_being_hunted);


        dylansLayout = (ViewGroup) findViewById(R.id.dylansLayout);

        setContentView(R.layout.activity_you_are_being_hunted);
        timerValue = (TextView) findViewById(R.id.timerValue);


        timerCount = new MyCount(11 * 1000, 1000);
        timerCount.start();
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //some script here
            timerValue.setText("-BEGIN HUNT-");
            timerValue.setTextSize(50);

            TranslateAnimation trans = new TranslateAnimation(0, 0, 0, 1600);
            trans.setDuration(3000);
            trans.setInterpolator(new AccelerateInterpolator(1.0f));
            timerValue.startAnimation(trans);
            onAnimationEnd();
        }


        public void onAnimationEnd() {
            Intent intent = new Intent(YouAreBeingHunted.this, MapsActivity.class);
            startActivity(intent);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //some script here
            timerValue.setText("Time Left: " + millisUntilFinished / 1000);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_are_being_hunted, menu);
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
