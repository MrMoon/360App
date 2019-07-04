package com.ma.moon.moonapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Database.ProfileModule;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.User;

public class ProfileActivity extends AppCompatActivity implements Basics, Components {

    //Variables and Objects:;
    private Button btnDashboard, btnCategories, btnLogout;
    private FirebaseAuth auth;
    private TextView txtEmail, txtProfile, txtName;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private ImageView imageView;
    private String userName, userEmail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Declarations:
        declarations();

        //Setting up the Profile:
        setupProfile();
    }

    @Override
    public void onClick(View v) {
        if (v == btnCategories) {
            updateUI(this, CategoriesActivity.class);
            ProfileActivity.this.finish();
        }
        if (v == btnDashboard) {
            updateUI(this, DashboardActivity.class);
            ProfileActivity.this.finish();
        }
        if (v == btnLogout) {
            signOut();
            updateUI(this, LoginActivity.class);
            ProfileActivity.this.finish();
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        progressDialog.dismiss();
        this.finish();
    }

    private void setupProfile() {
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            readData();
        } else {
            makeToast(Constants.ERROR);
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    @Override
    public void writeData() {

    }

    @Override
    public void readData() {
        firebase();
        reference = FirebaseDatabase.getInstance().getReference().child(Constants.DATABASE_PATH_USERS);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        ProfileModule module = snapshot.getValue(ProfileModule.class);
                        Toast.makeText(ProfileActivity.this, module.getFullname() + " - " + module.getEmail(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, "Can't convert", Toast.LENGTH_SHORT).show();
                    }
                }
             }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ProfileModule profileModule = dataSnapshot.getValue(ProfileModule.class);
//                if (profileModule != null) {
//                    txtProfile.setText(profileModule.getFullname());
//                    txtEmail.setText(profileModule.getEmail());
//                    txtName.setText(profileModule.getFullname());
//                } else {
//                    txtName.setText(user.getDisplayName());
//                    txtEmail.setText(user.getEmail());
//                    txtProfile.setText(user.getDisplayName());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void firebase() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnDashboard = findViewById(R.id.btnHomeProfile);
        btnCategories = findViewById(R.id.btnCategoriesProfile);
        btnLogout = findViewById(R.id.btnLogOut);

        //Listeners:
        btnDashboard.setOnClickListener(this);
        btnCategories.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {
        txtEmail = findViewById(R.id.txtEmailProfile);
        txtName = findViewById(R.id.txtNameProfile);
        txtProfile = findViewById(R.id.LogoTxtLogin);
    }

    @Override
    public void declarations() {
        buttonsDeclaration();
        textDeclaration();
        imageView = findViewById(R.id.imgProflie);
        progressDialog = new ProgressDialog(this);
    }
}
