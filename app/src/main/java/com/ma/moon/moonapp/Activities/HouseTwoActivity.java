package com.ma.moon.moonapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.House;

import java.util.ArrayList;

public class HouseTwoActivity extends AppCompatActivity {

    //Variables and Objects:
    private GyroscopeObserver gyroscopeObserver;
    private PanoramaImageView panoramaImageView;
    private TextView txtName,txtPrice,txtPerX;
    private House house;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_two);

        /*//Objects:
        txtName = (TextView) findViewById(R.id.txtHouseTwoPerX);
        txtPrice = (TextView) findViewById(R.id.txtHouseTwoPrice);
        txtPerX = (TextView) findViewById(R.id.txtHouseTwoName);

        //House SetUp:
        house = new House("Woodlands","349.99","/Per Month ");
        txtPrice.setText(house.getPrice());
        txtName.setText(house.getName());
        txtPerX.setText(house.getPerX());
*/
        //Setting Up the Panorama:
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI/9);

        panoramaImageView = (PanoramaImageView)findViewById(R.id.panoramaHouseTwo);
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
}
