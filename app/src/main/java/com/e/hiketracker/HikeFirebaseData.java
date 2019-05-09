package com.e.hiketracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HikeFirebaseData {

    DatabaseReference myHikeDbRef;
    public static final String HikeDataTag = "Hike Data";
    FirebaseAuth mAuth;
    private String userId;


    public DatabaseReference open(AppCompatActivity mainActivity)  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myHikeDbRef = database.getReference(HikeDataTag);

        // set the user id for the current logged in user
        userId = getUserId(mainActivity);
        Log.d("CIS3334", "Database Reference open() userId = " + userId);

        return myHikeDbRef;
    }

    public Hike addHike( String name,String distance, String time) {
        // ---- Get a new database key for the vote
        String key = myHikeDbRef.child(HikeDataTag).push().getKey();
//        String key = "REPLACE THIS WITH KEY FROM DATABASE";
        // ---- set up the hike object
        Hike newHike = new Hike(key, name, distance, time);
        Log.d("CIS3334", "New Hike created.");
        Log.d("CIS3334", "Time =" + time);
        //Hike newHike = new Hike(key, name, time);
        // ---- write the vote to Firebase
        myHikeDbRef.child("user").child(userId).child(key).setValue(newHike);
        Log.d("CIS3334", "Hike added to database");
        return newHike;
    }

    public List<Hike> getAllHikes(DataSnapshot dataSnapshot) {
        List<Hike> hikeList = new ArrayList<Hike>();
        Log.d("CIS3334", "Starting for each loop");
        for (DataSnapshot data : dataSnapshot.child("user").child(userId).getChildren()) {
            Hike hike = data.getValue(Hike.class);
            hikeList.add(hike);
            //FIXME these don't sho in Logcat
            Log.d("CIS3334", "populating hike list user Id = " + userId);
            Log.d("CIS3334", "Time = " + hike.getsTime());
        }
        return hikeList;
    }

    public Double getTotalTime(DataSnapshot dataSnapshot){
        Double totalTime = 0.0;

        for (DataSnapshot data : dataSnapshot.child("user").child(userId).getChildren()) {
            Hike hike = data.getValue(Hike.class);
            totalTime = totalTime + Double.parseDouble(hike.getsTime());
        }

        return totalTime;
    }

    public Double getTotalDistance(DataSnapshot dataSnapshot) {
        Double totalDistance = 0.0;

        for(DataSnapshot data : dataSnapshot.child("user").child(userId).getChildren()){
            Hike hike = data.getValue(Hike.class);
            totalDistance = totalDistance + Double.parseDouble(hike.getsDistance());
        }
        return totalDistance;
    }

    // get the current logged in user's id from Firebase
    private String getUserId(AppCompatActivity activity) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // User is signed out
            Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
            Intent signInIntent = new Intent(activity, LoginActivity.class);
            activity.startActivity(signInIntent);
        }
        return user.getUid();
    }
}
