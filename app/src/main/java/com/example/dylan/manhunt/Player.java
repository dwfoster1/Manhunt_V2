package com.example.dylan.manhunt;

/**
 * This class is very basic, but handles all the information we need to get the Player data
 * @author Dylan Foster
 * @version 5/416
 */
public class Player {
    /** Player name */
    private String name;
    /** Player latitude */
    private double lat;
    /** Player longitude */
    private double lng;

    /**
     * Set the player's username.
     * @param name the username to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This gets the name of the user
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the latitude of each player
     * @param lat - latitude coordinate
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Gets the latitude that we need for each player
     * @return lat - the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets the longitude
     * @param lng - the longitude
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Gets the longitude
     * @return lng - the longitude
     */
    public double getLng() {
        return lng;
    }
}