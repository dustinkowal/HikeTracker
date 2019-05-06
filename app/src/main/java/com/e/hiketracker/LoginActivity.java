package com.e.hiketracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    Button buttonCreate;
    TextInputEditText textInputEmail;
    TextInputEditText textInputPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreate = (Button) findViewById(R.id.buttonCreateAccount);
        textInputEmail = (TextInputEditText) findViewById(R.id.textInputEmail);
        textInputPassword = (TextInputEditText) findViewById(R.id.textInputPassword);

        mAuth = FirebaseAuth.getInstance();

        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void createNewUser(){
        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration unsuccessful...", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void login(){
        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "Login--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    Toast.makeText(LoginActivity.this, "Login Successful!",Toast.LENGTH_LONG).show();
                }
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
