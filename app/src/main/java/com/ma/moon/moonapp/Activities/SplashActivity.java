package com.ma.moon.moonapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ma.moon.moonapp.R;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();

    }

    //Splash Method:
    private void splash() {
        new Handler().postDelayed(this::checkUser, 2000);
    }

    //Checking If The User is already Logged in or Not:
    private void checkUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();

        }
    }
}
