package com.e.hiketracker;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HikeFirebaseData {

    DatabaseReference myHikeDbRef;
    public static final String HikeDataTag = "Hike Data";


    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myHikeDbRef = database.getReference(HikeDataTag);
        return myHikeDbRef;
    }

    public Hike addHike( String name, int time) {
        // ---- Get a new database key for the vote
        String key = myHikeDbRef.child(HikeDataTag).push().getKey();
//        String key = "REPLACE THIS WITH KEY FROM DATABASE";
        // ---- set up the fish object
        int points = time * 2;
        Hike newHike = new Hike(key, name, points);
        // ---- write the vote to Firebase
        myHikeDbRef.child(key).setValue(newHike);
        return newHike;
    }
    /*
    FIXME for the extra feature of set list of hikes
    public void createHikes() {
        // ---- Get a new database key for the vote
        String key = myHikeDbRef.child(HikeDataTag).push().getKey();
        // String key = "REPLACE THIS WITH KEY FROM DATABASE";
        // ---- set up the fish object
        Hike engerTower = new Hike(key, "Enger Tower", 2, 4);
        Hike peidmontTrail = new Hike(key,"Peidmont Trail", 6, 7 );
    }
*/

    public List<Hike> getAllHikes(DataSnapshot dataSnapshot) {
        List<Hike> hikeList = new ArrayList<Hike>();
        Log.d("CIS3334", "Starting for each loop");
        //FIXME this causes app to crash
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Hike hike = data.getValue(Hike.class);
            hikeList.add(hike);
            Log.d("CIS3334", "populating hike list: ");
        }
        return hikeList;
    }
}
