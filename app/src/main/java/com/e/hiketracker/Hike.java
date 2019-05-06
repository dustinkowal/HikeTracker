package com.e.hiketracker;

import android.util.Log;

import java.io.Serializable;

public class Hike implements Serializable {

    private String key;
    private String trailName;
    private String latitude;
    private String longitude;
    private String time;
    private String sDistance;

    public Hike () {

    }

    /*
    public Hike (String key, String trailName, Double distance, Double time) {
        this.key = key;
        this.trailName = trailName;
        this.dTime = time;
        this.distance = distance;
    }*/

    public Hike (String key, String trailName, String miles, String time) {
        this.key = key;
        this.trailName = trailName;
        this.time = time;
        this.sDistance = miles;
    }

    public String getName(){
        return this.trailName;
    }

    public String getsDistance(){
        return this.sDistance;
    }
    public String getsTime() {
        return this.time;
    }

    public void setName(String name){
        this.trailName = name;
    }

    public void setDistance(String distance) {
        this.sDistance= distance;
    }

    public void setsTime(String time){
        this.time = time;
    }
}
