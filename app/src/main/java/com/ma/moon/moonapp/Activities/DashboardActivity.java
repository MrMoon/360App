package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.widget.GridView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import com.ma.moon.moonapp.Adapters.ImageAdapter;
import com.ma.moon.moonapp.Adapters.ViewPagerAdapter0;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.Interfaces.WriteData;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.Profile;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements Basics, Components {

    //Variables and Objects:
    private GridView gridView;
    private String[] txtList = {"House1",
            "House2", "House3", "House4",
            "House5", "House6", "House7", "House8",
            "House9"};
    int[] imgList = {R.drawable.beti_salon_panorama_logo, R.drawable.numonelogo,
            R.drawable.ozerologo, R.drawable.image4,
            R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image8, R.drawable.image9};
    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private Button btnCategories, btnProfile;
    private ImageAdapter imageAdapter;
    private ViewPagerAdapter0 viewPagerAdapter0;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Declarations:
        declarations();

        //Firebase:
        firebase();

        //Grid
        gridSlider();

        //Checking if the E-Mail is Verified:
        //emailCheck();
    }

    private void emailCheck() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null && !firebaseUser.isEmailVerified()) {
            makeToast(Constants.EMAIL_VERIFICATION);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnCategories) {
            updateUI(this, CategoriesActivity.class);
        }
        if (v == btnProfile) {
            updateUI(this, ProfileActivity.class);
        }
    }

    private void gridSlider() {
        //Setting Up the Grid:
        imageAdapter = new ImageAdapter(this, txtList, imgList);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (txtList[position].equals("House1")) {
                updateUI(this, HouseZeroActivity.class);
                finish();
            } else if (txtList[position].equals("House2")) {
                updateUI(this, HouseOneActivity.class);
                finish();
            } else {
                makeToast(Constants.STILL_WORKING_ON_IT);
            }
        });

        //Setting Up the ViewPager with the Slider:
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter0 = new ViewPagerAdapter0(this);
        viewPager.setAdapter(viewPagerAdapter0);

        //Setting Up the slider:
        dotscount = viewPagerAdapter0.getCount();
        dots = new ImageView[dotscount];
        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            sliderDotspanel = (LinearLayout) findViewById(R.id.ll_dots);
            params.setMargins(8, 0, 8, 0);
            try {
                sliderDotspanel.addView(dots[i], params);
            } catch (Exception e) {
                System.out.println(e.toString());
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots2));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots2));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(DashboardActivity.this, R.drawable.dots));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
        reference = firebaseDatabase.getReference(Constants.DATABASE_PATH_DASHBOARD);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                makeToast(Constants.ERROR);
            }
        };
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnCategories = (Button) findViewById(R.id.btnCategoriesHome);
        btnProfile = (Button) findViewById(R.id.btnProfileHome);

        //Listeners:
        btnCategories.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {

    }

    @Override
    public void declarations() {
        buttonsDeclaration();
        textDeclaration();
    }


    @Override
    public void writeData() {

    }

    @Override
    public void readData() {

    }
}