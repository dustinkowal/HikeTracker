package com.e.hiketracker;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class AddHikeActivity extends AppCompatActivity {

    TextInputEditText textInputTime;
    TextInputEditText textInputTrailName;
    TextInputEditText textInputDistance;
    Button buttonAdd;
    HikeFirebaseData hikeDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);

        textInputTrailName = (TextInputEditText) findViewById(R.id.textInputTrailName);
        textInputTime = (TextInputEditText) findViewById(R.id.textInputTime);
        textInputDistance = (TextInputEditText) findViewById(R.id.textInputDistance);

        hikeDataSource = new HikeFirebaseData();
       //DatabaseReference myDbRef =
        hikeDataSource.open(this);

        buttonAdd = (Button) findViewById(R.id.buttonAddHike);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                String time;
                String distance;

                name = textInputTrailName.getText().toString();
                time = textInputTime.getText().toString();
                distance = textInputDistance.getText().toString();

                hikeDataSource.addHike(name, distance, time);
                Toast.makeText(getApplicationContext(), "Hike Added!", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}
