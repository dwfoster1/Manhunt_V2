package com.example.dylan.manhunt;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Point;
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

public class MapsActivity extends FragmentActivity implements

        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String TAG = MapsActivity.class.getSimpleName();

    LocationManager lm;
    double lat = 35.31205228, long1 = -83.18095431;    //Defining Latitude & Longitude

    private static final long POINT_RADIUS = 3; // in Meters
    private static final long PROX_ALERT_EXPIRATION = -1;

    //private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    //private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";

    private static final String PROX_ALERT_INTENT =
            "com.example.dylan.manhunt.ProximityReceiver";

    private static final double STEP_SIZE = .00000000000002;

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Intent i = new Intent("com.example.dylan.manhunt.ProximityReceiver");

        //Custom Action
        //PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), -1, i, 0);

        //-1 means alert never expires
        //if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for Activity#requestPermissions for more details.
        //    return;
        //}

        // marker 1 gs_icon location, within 3 meters, never removed, pending intent
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        lm.addProximityAlert(
                lat, // the latitude of the central point of the alert region
                long1, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityReceiver(), filter);



    setUpMapIfNeeded();

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

    /**public void moveMarkerRandomly(Marker mark) {  // could write this in the Marker class as a function of Marker, so you can do aMarker.moveMarkerRandomly() instead.
        //need to define constant: STEP_SIZE, bigger number means a larger jump in the point and faster movement, smaller number means smoother movement, this code should update the position of the marker like the person took a step, and should be called multiple times a second for smooth movement, or every few seconds with a larger step size for a jumpy movement, but more efficient.

        Point oldLocation = mark.();   //assuming a location returns a point containing an X,Y coordinates of the marker
        Point newLocation = new Point(); //will later hold the value of the new location
        Boolean flag = true;

        //because of the loop below, it would technically be correct to throw an error here if the "oldLocation" was off campus already, since if a marker which is off campus for any reason is ever passed here it will cause an infinite loop; I'm lazy and not writing here though :x

        while(flag) { //need to loop in case we randomly step off campus, then instead try to step randomly in another direction to remain on campus
            xStep = (rand.random() * STEP_SIZE * 2) - STEP_SIZE ;
            yStep = (rand.random() * STEP_SIZE * 2) - STEP_SIZE;
            newLocation = oldLocation + new Point(xStep, yStep) //I /think/ you can add points like this? if not then: = new Point(oldLocation.x() + xStep, oldLocation.y() + yStep());
            flag = !isOnCampus(newLocation);
        }

        mark.setPosition(new LatLng(newLocation));  //I'm unsure if this is a function you need to define yourself, or find in an API, but it's important, and will be needed to randomly move the points as well as to move them when they are actual people, this should be the function that actually in the code changes the lat/lon of the marker once we know the new location to move it to
    }

    public boolean isOnCampus(Point2D point) {
        return point.distance(locProx) < RADIUS;
    }
     */

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
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
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
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
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
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

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(20)
                .tilt(30)
                .build();
        center = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(center);
        //mMap.animateCamera(zoom);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        LatLng loc = new LatLng(35.30990511001521, -83.18256941623986);
        MarkerOptions marker = new MarkerOptions().position(loc).title("Hunter!").draggable(true);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.gs_icon));

        LatLng loc2 = new LatLng(35.31002111546393, -83.18308398127556);
        MarkerOptions marker2 = new MarkerOptions().position(loc2).title("Prey!").draggable(true);
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.sm_icon));

        LatLng loc3 = new LatLng(35.31002330424439, -83.1817401945591);
        MarkerOptions marker3 = new MarkerOptions().position(loc3).title("Prey!").draggable(true);
        marker3.icon(BitmapDescriptorFactory.fromResource(R.drawable.sm_icon));

        LatLng loc4 = new LatLng(35.31205228, -83.18095431);
        MarkerOptions marker4 = new MarkerOptions().position(loc4).title("Hunter!").draggable(true);
        marker4.icon(BitmapDescriptorFactory.fromResource(R.drawable.gs_icon));


        mMap.addMarker(marker);
        mMap.addMarker(marker2);
        mMap.addMarker(marker3);
        mMap.addMarker(marker4);

    }

   /** private void addProximityAlert(double latitude, double longitude) {

        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        lm.addProximityAlert(
                latitude, // the latitude of the central point of the alert region
                longitude, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityReceiver(), filter);

    }*/

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
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
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }
}

