package com.example.dylan.manhunt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        // The receiver gets the Context & the Intent that fired the broadcast as arg0 & agr1

        String k = LocationManager.KEY_PROXIMITY_ENTERING;
        // Key for determining whether user is leaving or entering

        boolean state = arg1.getBooleanExtra(k, false);
        //Gives whether the user is entering or leaving in boolean form

        if(state){
            // Call the Notification Service or anything else that you would like to do here
            Toast.makeText(arg0, "You have entered tag zone", Toast.LENGTH_SHORT).show();
        }else{
            //Other custom Notification
            Toast.makeText(arg0, "You have left tag zone", Toast.LENGTH_SHORT).show();

        }

    }


}