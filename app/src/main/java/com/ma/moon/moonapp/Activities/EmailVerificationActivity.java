package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.Interfaces.MakeToast;
import com.ma.moon.moonapp.R;

public class EmailVerificationActivity extends AppCompatActivity implements Basics, Components {

    //Variables and Objects:
    private Button btnBack;
    private TextView txtEmailVerification;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        declarations();

        firebase();

        emailVerification();

    }

    private void emailVerification() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            txtEmailVerification.setText(String.format("An E-Mail was send to %s", firebaseUser.getEmail()));
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (task.isSuccessful()) {
                    updateUI(this, DashboardActivity.class);
                    writeData();
                    this.finish();
                } else {
                    if (!firebaseUser.isEmailVerified()) {
                        makeToast(Constants.EMAIL_VERIFICATION);
                    } else {
                        makeToast(Constants.TASK_IS_NOT_SUCCESSFUL);
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            updateUI(this, RegisterActivity.class);
            this.finish();
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnBack = (Button) findViewById(R.id.btnBackEmailVerification);

        //Listeners:
        btnBack.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {
        txtEmailVerification = (TextView) findViewById(R.id.txtEmailVerification);
    }

    @Override
    public void declarations() {
        textDeclaration();
        buttonsDeclaration();
    }

    @Override
    public void writeData() {
    }

    @Override
    public void readData() {

    }
}
