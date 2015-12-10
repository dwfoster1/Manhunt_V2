package com.example.dylan.manhunt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Activity that receives broadcasts for MapsActivity which are proximity alert intents.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class ProximityReceiver extends BroadcastReceiver {

    @Override
    /**
     * A method that receives an intent from MapsActivity when a user has entered a
     * proximity alert area.
     *
     * @param arg0 - the state of the intent that was fired as false
     * @param arg1 - the state of the intent that was fired as true
     */
    public void onReceive(Context arg0, Intent arg1) {
        // The receiver gets the Context & the Intent that fired the broadcast as arg0 & agr1
        String k = LocationManager.KEY_PROXIMITY_ENTERING;

        // Key for determining whether user is leaving or entering
        boolean state = arg1.getBooleanExtra(k, false);

        //Gives whether the user is entering or leaving in boolean form

        if(state){
            // Call the Notification Service or anything else that you would like to do here
            Toast.makeText(arg0, "You have entered tag zone", Toast.LENGTH_SHORT).show();
            //notificationTitle="Proximity - Entry";
            //notificationContent="Entered the region";
            //tickerMessage = "Entered the region";
        }else{
            //Other custom Notification
            Toast.makeText(arg0, "You have left tag zone", Toast.LENGTH_SHORT).show();
            //notificationTitle="Proximity - Exit";
            //notificationContent="Exited the region";
            //tickerMessage = "Exited the region";
        }
    }
    //Intent notificationIntent = new Intent(getApplicationContext(),NotificationView.class);
    //notificationIntent.putExtra("content", notificationContent );

    /** This is needed to make this intent different from its previous intents */
    //notificationIntent.setData(Uri.parse("tel:/"+ (int)System.currentTimeMillis()));

    /** Creating different tasks for each notification. See the flag Intent.FLAG_ACTIVITY_NEW_TASK */
    //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

    /** Getting the System service NotificationManager */
    // NotificationManager nManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

    /** Configuring notification builder to create a notification */
    // NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
    //        .setWhen(System.currentTimeMillis())
    //        .setContentText(notificationContent)
    //        .setContentTitle(notificationTitle)
    //        .setSmallIcon(R.drawable.ic_launcher)
    //        .setAutoCancel(true)
    //        .setTicker(tickerMessage)
    //        .setContentIntent(pendingIntent);

    /** Creating a notification from the notification builder */
    // Notification notification = notificationBuilder.build();

    /** Sending the notification to system.
     * The first argument ensures that each notification is having a unique id
     * If two notifications share same notification id, then the last notification replaces the first notification
     * */
    // nManager.notify((int)System.currentTimeMillis(), notification);

    /** Finishes the execution of this activity */
    // finish();
}
