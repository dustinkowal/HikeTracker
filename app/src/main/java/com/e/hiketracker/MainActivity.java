package com.e.hiketracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * MainActivity for the App
 */
public class MainActivity extends AppCompatActivity {


    ListView listViewHike;
    HikeFirebaseData hikeDataSource;
    DatabaseReference myHikeDbRef;
    List<Hike> hikeList;
    ArrayAdapter<Hike> hikeAdapter;
    int positionSelected;

    TextView textViewTotalTime;
    TextView textViewTotalDistance;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkUserAuthenticated();
        setupListView();
        setupFirebaseDataChange();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //sends message if there is no user logged in
        if(user == null){
            Toast.makeText(getApplicationContext(), "Login to view trails and add hikes.", Toast.LENGTH_LONG).show();
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(signInIntent);
                }
            }
        };

        //setup textViews to display total time and distance
        textViewTotalDistance = (TextView) findViewById(R.id.textViewTotalDistance);
        textViewTotalTime = (TextView) findViewById(R.id.textViewTotalTime);


    }

    /**
     * Checks the authentication for the current user
     *
     */
    private void checkUserAuthenticated() {
        mAuth = FirebaseAuth.getInstance(); //declare object for Firebase
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(signInIntent);
                }
            }
        };
    }

    /**
     * Tracks if data changes in the database and changes the ListView accordingly
     *
     */
    private void setupFirebaseDataChange() {
        hikeDataSource = new HikeFirebaseData();
        myHikeDbRef = hikeDataSource.open(this);
        myHikeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                hikeList = hikeDataSource.getAllHikes(dataSnapshot);
                Log.d("CIS3334", "retrieved hike list");
                // Instantiate a custom adapter for displaying each fish
                hikeAdapter = new HikeAdapter(MainActivity.this, android.R.layout.simple_list_item_single_choice, hikeList);
                // Apply the adapter to the list
                Log.d("CIS3334", "Setting adapter");
                listViewHike.setAdapter(hikeAdapter);

                //change totals
                textViewTotalDistance.setText(hikeDataSource.getTotalDistance(dataSnapshot).toString());
                textViewTotalTime.setText(hikeDataSource.getTotalTime(dataSnapshot).toString());


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CIS3334", "onCancelled: ");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Used to track which item the user selects from the menu
     *
     * @param item
     * @return item selected by user
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
            finish();
            startActivity(loginIntent);
        }
        if (id == R.id.action_add_hike) {
            Intent addIntent = new Intent(this, AddHikeActivity.class);
            finish();
            startActivity(addIntent);

        }
        if (id == R.id.action_trails) {
            Intent trailIntent = new Intent(this, MainActivity.class);
            finish();
            startActivity(trailIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Creates the ListView
     *
     */
    private void setupListView() {
        listViewHike = (ListView) findViewById(R.id.listViewHike);
        listViewHike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                //Log.d("MAIN", "Fish selected at position " + positionSelected);
            }
        });
    }

    /**
     * Updates Authentication listener when the app starts
     *
     */
    @Override
    public void onStart() {
        //initiate the authentication listener
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); // update the listener on the users place
    }

    /**
     * Signs out user when application is closed.
     *
     */
    @Override
    public void onStop() {
        //discontinue the authentication
        super.onStop();
        if (mAuthListener != null) {
            //signout current user stop using because it would sign out user when switching activities
            /*mAuth.signOut();
            if(mAuth.getCurrentUser() == null){
                Log.d("CIS 3334", "User has been logged out");
            }
            else{
                Log.d("CIS 3334", "Error in logging user out");
            }*/
            mAuth.removeAuthStateListener(mAuthListener); // remove the listener
        }
    }


}