package com.example.dylan.manhunt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

/**
 * This class displays the contents of SplashScreen for 4 seconds at the start of the boot sequence.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class SplashScreen extends Activity {

    /** Duration of wait set to 4 seconds */
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    /**
     * Built in method in android that builds necessary instance state and displays the layout and
     * also sets a timer for which to display the splash screen.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(SPLASH_DISPLAY_LENGTH);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    /**
     * Pauses the map if its connected and then disconnects after removing location updates.
     */
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    /**
     * Default android method that displays information on an action bar if it is present.
     *
     * @param menu - menu that appears as an action bar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
