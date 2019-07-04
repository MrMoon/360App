package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.Adapters.ImageAdapter;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.Test.TestActivity;

public class CategoriesActivity extends AppCompatActivity implements Basics,Components {
    //Variables and Objects:
    private GridView gridView;
    private String[] txtList = {"Houses", "Cars", "Mobiles", "Laptops"};
    private int[] imgList = {R.drawable.ic_house, R.drawable.ic_car, R.drawable.ic_moblie, R.drawable.ic_laptop};
    private Button btnDashboard,btnProfile,btnTest;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //Declarations:
        declarations();

        //Grid:
        gridCategories();

        //Firebase:
        firebase();
    }

    @Override
    public void onClick(View v) {
        if (v == btnDashboard){
            updateUI(this,DashboardActivity.class);
            finish();
        }
        if (v == btnProfile){
            updateUI(this,ProfileActivity.class);
            finish();
        }
        if (v == btnTest){
            updateUI(this,TestActivity.class);
            finish();
        }
    }

    private void gridCategories(){
        ImageAdapter imageAdapter = new ImageAdapter(this,txtList,imgList);
        gridView = (GridView) findViewById(R.id.gridViewCategories);
        try {
            gridView.setAdapter(imageAdapter);
            gridView.setOnItemClickListener((parent, view, position, id) -> {
                if (txtList[position].equals(Constants.CATEGORIES_HOUSES)) {
                    updateUI(this,AllHousesActivity.class);
                } else if (txtList[position].equals(Constants.CATEGORIES_CARS)) {

                } else if (txtList[position].equals(Constants.CATEGORIES_MOBILES)) {

                } else if (txtList[position].equals(Constants.CATEGORIES_LAPTOPS)) {

                }
            });
        } catch (Exception e) {
           makeToast(Constants.ERROR);
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {
        Intent intent = new Intent(context,aClass);
        startActivity(intent);
    }

    @Override
    public void writeData() {

    }

    @Override
    public void readData() {

    }

    @Override
    public void firebase() {
        //Firebase:
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(Constants.DATABASE_PATH_CATEGORIES);
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnDashboard  = (Button) findViewById(R.id.btnDashboardCate);
        btnProfile  = (Button) findViewById(R.id.btnProfileCate);
        btnTest = (Button) findViewById(R.id.btnTest);

        //Listeners:
        btnDashboard.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {

    }

    @Override
    public void declarations() {
        buttonsDeclaration();
        textDeclaration();
    }
}