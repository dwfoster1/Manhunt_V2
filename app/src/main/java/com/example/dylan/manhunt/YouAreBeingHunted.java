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
        /**
        timerTextView = (TextView) findViewById(R.id.timer);

        Button b = (Button) findViewById(R.id.button_start);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("Proceed to map")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("Proceed to map");
                }
            }
        });




        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(YouAreBeingHunted.this, TimesUp.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();*/

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
            trans.setDuration(6000);
            trans.setInterpolator(new AccelerateInterpolator(1.0f));
            timerValue.startAnimation(trans);
            onAnimationEnd();




            //b
            //Intent intent = new Intent(YouAreBeingHunted.this, MapsActivity.class);
            //startActivity(intent);
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

/**
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }
*/
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
