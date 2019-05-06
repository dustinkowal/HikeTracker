package com.e.hiketracker;

import java.io.Serializable;

public class Hike implements Serializable {

    private String key;
    private String timeSpent;
    private int distance;
    private String trailName;
    private String latitude;
    private String longitude;
    private int time;

    public Hike () {

    }
    public Hike (String key, String trailName) {
        this.key = key;
        this.trailName = trailName;
    }

    public Hike (String key, String name, int time){
        this.key = key;
        this.trailName = name;
        this.time = time;
    }

    public Hike (String key, String trailName, int miles, int time) {
        this.key = key;
        this.trailName = trailName;
        this.time = time;
        this.distance = miles;
    }

    public String getName(){
        return this.trailName;
    }

    public int getDistance(){
        return this.distance;
    }

    public String getTime() {
        return Integer.toString(this.time);
    }

    public void setName(String name){
        this.trailName = name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTime(int time){
        this.time = time;
    }
}
