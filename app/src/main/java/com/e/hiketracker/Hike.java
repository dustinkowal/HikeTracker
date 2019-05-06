package com.e.hiketracker;

import java.io.Serializable;

public class Hike implements Serializable {

    private String key;
    private String timeSpent;
    private int distance;
    private String trailName;
    private String latitude;
    private String longitude;
    private int points;

    public Hike (String key, String trailName) {
        this.key = key;
        this.trailName = trailName;
    }

    public Hike (String key, String name, int points){
        this.key = key;
        this.trailName = name;
        this.points = points;
    }

    public Hike (String key, String trailName, int miles, int points) {
        this.key = key;
        this.trailName = trailName;
        this.points = points;
        this.distance = miles;
    }

    public String getName(){
        return this.trailName;
    }

    public int getDistance(){
        return this.distance;
    }

    public int getPoints() {
        return this.points;
    }

    public void setName(String name){
        this.trailName = name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPoints(int points){
        this.points = points;
    }
}
