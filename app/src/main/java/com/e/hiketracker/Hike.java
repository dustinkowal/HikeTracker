package com.e.hiketracker;

import android.util.Log;

import java.io.Serializable;

/**
 * Represents a hike
 * Tracks name, time, and distance
 */
public class Hike implements Serializable {

    private String key;
    private String trailName;
    private String latitude;
    private String longitude;
    private String time;
    private String sDistance;

    public Hike () {

    }


    /**
     * Object that stores Hike information
     *
     */
    public Hike (String key, String trailName, String miles, String time) {
        this.key = key;
        this.trailName = trailName;
        this.time = time;
        this.sDistance = miles;
    }

    /**
     * retrieves trail name
     * @return trail name
     */
    public String getName(){
        return this.trailName;
    }

    /**
     * retrieves distance
     * @return distance
     */
    public String getsDistance(){
        return this.sDistance;
    }

    /**
     * retrieves time
     * @return time
     */
    public String getsTime() {
        return this.time;
    }

    /**
     * sets trail name
     * @param name
     */
    public void setName(String name){
        this.trailName = name;
    }

    /**
     * sets time
     * @param time
     */
    public void setsTime(String time) {
        this.time = time;
    }

    /**
     * sets distance
     * @param distance
     */
    public void setsDistance (String distance) {this.sDistance = distance;}

}
