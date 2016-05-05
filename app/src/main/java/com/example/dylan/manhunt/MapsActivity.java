package com.example.dylan.manhunt;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Activity that handles all of the google map code of building, and maintaining connection.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class MapsActivity extends FragmentActivity implements

        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    /** Gets the MapsActivity class name and stores into a tag */
    public static final String TAG = MapsActivity.class.getSimpleName();

    /** Location manager */
    LocationManager lm;

    /** Approximate center of the school (the fountain) for testing movement */
    private static final LatLng SCHOOL_CENTER = new LatLng(35.30990511001521, -83.18256941623986);

    /** Coordinates for testing */
    double lat = 35.31205228, long1 = -83.18095431;

    /** Norton walkway coordinates for me to test later */
    double lat2 = 35.31337426, long2 = -83.18507552;

    /** Approximate radius of the school for testing */
    private static final long SCHOOL_RADIUS = 1000;

    /** Radius in which a player can be tagged */
    private static final long POINT_RADIUS = 5; // in Meters

    /**  Proximity alert set to -1 (means alert never expires) */
    private static final long PROX_ALERT_EXPIRATION = -1;

    /** Alert that will be sent and its location destination */
    private static final String PROX_ALERT_INTENT =
            "com.example.dylan.manhunt.ProximityReceiver";

    /** What the fake players will move by to have a "smooth" animation */
    private static final double STEP_SIZE = .00000000000002;

    /** Time for an attempt to be made to reconnect in milliseconds */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    /** The map you will be seeing */
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    /** The google api client required for map to work */
    private GoogleApiClient mGoogleApiClient;

    /** What requests the current location of any given player */
    private LocationRequest mLocationRequest;

    @Override
    /**
     * Built in method in android that builds necessary instance state and button presses
     * that fire off intents to begin their specified activity including setting up the proximity
     * alert creation.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        // marker 1 icon location, within 3 meters, never removed, pending intent
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        // needs a permission check eventually, but works without (looks scary)
        lm.addProximityAlert(
                lat, // the latitude of the central point of the alert region
                long1, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );

        lm.addProximityAlert(lat2, long2, POINT_RADIUS, PROX_ALERT_EXPIRATION, proximityIntent);

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityReceiver(), filter);

        setUpMapIfNeeded();
        //ReadXml.parse();

        // Create the api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }

    /************** Demonstration code that was used for a logic test for myself ******************/


    /**
    public void moveMarkerRandomly(Marker mark) {  // could write this in the Marker class as a function of Marker, so you can do aMarker.moveMarkerRandomly() instead.
        //need to define constant: STEP_SIZE, bigger number means a larger jump in the point and faster movement, smaller number means smoother movement, this code should update the position of the marker like the person took a step,
        // and should be called multiple times a second for smooth movement, or every few seconds with a larger step size for a jumpy movement, but more efficient.

        Point oldLocation = mark.();   //assuming a location returns a point containing an X,Y coordinates of the marker
        Point newLocation = new Point(); //will later hold the value of the new location
        Boolean flag = true;

        //because of the loop below, it would technically be correct to throw an error here if the "oldLocation" was off campus already,
        // since if a marker which is off campus for any reason is ever passed here it will cause an infinite loop.

        while(flag) { //need to loop in case we randomly step off campus, then instead try to step randomly in another direction to remain on campus
            xStep = (rand.random() * STEP_SIZE * 2) - STEP_SIZE ;
            yStep = (rand.random() * STEP_SIZE * 2) - STEP_SIZE;
            newLocation = oldLocation + new Point(xStep, yStep); //I /think/ you can add points like this? if not then: = new Point(oldLocation.x() + xStep, oldLocation.y() + yStep());
            flag = !isOnCampus(newLocation);
        }

        mark.setPosition(new LatLng(newLocation));  //I'm unsure if this is a function you need to define yourself, or find in an API, but it's important,
        // and will be needed to randomly move the points as well as to move them when they are actual people,
        // this should be the function that actually in the code changes the lat/long of the marker once we know the new location to move it to
    }

    public boolean isOnCampus(float point) {
        return point.distance(SCHOOL_CENTER) < SCHOOL_RADIUS;
    }*/


    /********************************* End of demonstration code **********************************/

    @Override
    /**
     * Resumes the connection to the map client and also sets up the map again if necessary.
     */
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    /**
     * Pauses the map if its connected and then disconnects after removing location updates.
     */
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call setUpMap() once when mMap is not null.
     * If it isn't installed (and com.google.android.gms.maps.MapView MapView) will show a prompt
     * for the user to install/update the Google Play services APK on their device.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {

            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMyLocationEnabled(true);
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
    }

    /**
     * Handles when a player changes location (moves on the map) and updates. Also builds specific
     * locations for markers to be added on the map.
     *
     * @param location - the location that the player is currently at
     */
    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(20)
                .tilt(30)
                .build();
        CameraUpdate center = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(center);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        /** Creates marker locations and customizes them for my purposes */
        LatLng loc = new LatLng(35.30990511001521, -83.18256941623986);
        MarkerOptions marker = new MarkerOptions().position(loc).title("Hunter!").draggable(true);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.scope3));

        LatLng loc2 = new LatLng(35.31002111546393, -83.18308398127556);
        MarkerOptions marker2 = new MarkerOptions().position(loc2).title("Survivor!").draggable(true);
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.hunted3));

        LatLng loc3 = new LatLng(35.31002330424439, -83.1817401945591);
        MarkerOptions marker3 = new MarkerOptions().position(loc3).title("Survivor!").draggable(true);
        marker3.icon(BitmapDescriptorFactory.fromResource(R.drawable.hunted3));

        LatLng loc4 = new LatLng(35.31205228, -83.18095431);
        MarkerOptions marker4 = new MarkerOptions().position(loc4).title("Hunter!").draggable(true);
        marker4.icon(BitmapDescriptorFactory.fromResource(R.drawable.scope3));

        LatLng loc5 = new LatLng(35.31337426, -83.18507552);
        MarkerOptions marker5 = new MarkerOptions().position(loc5).title("Hunter!").draggable(true);
        marker5.icon(BitmapDescriptorFactory.fromResource(R.drawable.scope3));

        /** add markers to map */
        mMap.addMarker(marker);
        mMap.addMarker(marker2);
        mMap.addMarker(marker3);
        mMap.addMarker(marker4);
        mMap.addMarker(marker5);

    }

    @Override
    /**
     * Once connected, finds location and requests updates on position.
     */
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(location);
        }
    }

    @Override
    /**
     * Handles the case where a connection gets suspended.
     */
    public void onConnectionSuspended(int i) {
        // Might need to complete this later
    }

    @Override
    /**
     * Handles if the connection failed and alerts user to an issue.
     */
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    /**
     * Handles the new location if a player has moved.
     */
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
        String type = "request";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);

    }
}

