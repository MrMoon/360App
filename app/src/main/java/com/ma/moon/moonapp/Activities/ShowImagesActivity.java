package com.ma.moon.moonapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.*;
import com.ma.moon.moonapp.Adapters.RecycleViewAdapter;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.Test.TestActivity;
import com.ma.moon.moonapp.pojo.Upload;

import java.util.LinkedList;
import java.util.List;

public class ShowImagesActivity extends AppCompatActivity implements Basics {

    //Objects and Variables:
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private List<Upload> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ma.moon.moonapp.R.layout.activity_show_images);

        //Objects:
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressDialog = new ProgressDialog(this);
        uploadList = new LinkedList<>();

        //RecycleView:
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Firebase:
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //Read Data and put it in the Recycle View:
        readData();

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
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Upload upload = snapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }

                adapter = new RecycleViewAdapter(getApplicationContext(), uploadList);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void firebase() {

    }

    @Override
    public void onClick(View v) {

    }
}