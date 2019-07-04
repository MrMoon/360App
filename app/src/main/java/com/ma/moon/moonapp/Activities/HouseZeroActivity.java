package com.ma.moon.moonapp.Activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.House;
import com.ma.moon.moonapp.pojo.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HouseZeroActivity extends AppCompatActivity {

    //Variables and Objects:
    private GyroscopeObserver gyroscopeObserver;
    private PanoramaImageView panoramaImageView;
    private House house;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private List<House> houseList;
    private String name,perX;
    private Double price;
    private TextView txtName,txtPrice,txtPerX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_zero);

        //Setting Up the Panorama:
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI/9);

        panoramaImageView = (PanoramaImageView) findViewById(R.id.panoramaHouseZero);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
        panoramaImageView.setImageResource(R.drawable.beti_salon_panorama);

    }



    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

}