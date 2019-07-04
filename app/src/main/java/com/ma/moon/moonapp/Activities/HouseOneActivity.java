package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HouseOneActivity extends AppCompatActivity implements Basics {

    //Variables and Objects:
    private GyroscopeObserver gyroscopeObserver;
    private PanoramaImageView panoramaImageView;
    private House house;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private List<House> houseList;
    private String name, perX;
    private Double price;
    private TextView txtName, txtPrice, txtPerX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_one);

       /* //Objects:
        txtName = (TextView) findViewById(R.id.txtHouseOneName);
        txtPrice = (TextView) findViewById(R.id.txtHouseOnePrice);
        txtPerX = (TextView) findViewById(R.id.txtHouseOnePerX);
        houseList = new ArrayList<>();

        //House SetUp:
        house = new House("Mill House.","399.99","/Per Month ");
        txtPrice.setText(house.getPrice());
        txtName.setText(house.getName());
        txtPerX.setText(house.getPerX());
*/
        //Setting Up the Panorama:
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);

        panoramaImageView = (PanoramaImageView) findViewById(R.id.panoramaHouseOne);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

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

    @Override
    public void firebase() {
        //Firebase:
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(Constants.DATABASE_PATH_HOUSES);
    }

    @Override
    public void makeToast(String message) {

    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {

    }

    @Override
    public void writeData() {

    }

    @Override
    public void readData() {

    }

    @Override
    public void onClick(View v) {

    }
}
