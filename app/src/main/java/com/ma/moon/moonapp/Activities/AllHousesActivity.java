package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.ma.moon.moonapp.Adapters.ImageAdapter;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.Interfaces.WriteData;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.HouseName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllHousesActivity extends AppCompatActivity implements Basics, Components {
    //Variables and Objects:
    private GridView gridView;
    private String[] txtHouses = {"RobinWood Farm", "Mill House", "Woodlands", "Treetops", "The Lodge", "RobinWood Farm", "Primrose Cottage", "Mill House"};
    private int[] imgList = {R.drawable.beti_salon_panorama_logo, R.drawable.realpanologo, R.drawable.ozerologo,
            R.drawable.zerologo, R.drawable.nzerologo, R.drawable.vzerologo, R.drawable.numonelogo, R.drawable.onethree};
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private ImageAdapter imageAdapter;
    private Button btnBack, btnSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_houses);

        declarations();

        //Setting Up the Grid:
        imageAdapter = new ImageAdapter(AllHousesActivity.this, txtHouses, imgList);
        gridView = (GridView) findViewById(R.id.gridViewAllHouses);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (txtHouses[position].equals("RobinWood Farm")) {
                updateUI(this, HouseZeroActivity.class);
            } else if (txtHouses[position].equals("Mill House")) {
                updateUI(this, HouseOneActivity.class);
            } else if (txtHouses[position].equals("Woodlands")) {
                updateUI(this, HouseTwoActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            updateUI(this, CategoriesActivity.class);
            AllHousesActivity.this.finish();
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {
        Intent intent = new Intent(context, aClass);
        startActivity(intent);
    }

    @Override
    public void firebase() {
        //Firebase:
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(Constants.DATABASE_PATH_HOUSES);
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSort = (Button) findViewById(R.id.btnSort);

        //Listeners:
        btnBack.setOnClickListener(this);
        btnSort.setOnClickListener(this);
    }

    @Override
    public void declarations() {
        buttonsDeclaration();
        textDeclaration();
    }

    @Override
    public void textDeclaration() {

    }

    @Override
    public void writeData() {

    }

    @Override
    public void readData() {

    }
}
