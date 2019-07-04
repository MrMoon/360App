package com.ma.moon.moonapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.R;

public class ResetPasswordActivity extends AppCompatActivity implements Basics, Components {

    //Variables and Objects:
    private Button btnLogin, btnResetPassword;
    private EditText txtEmail;
    private String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        declarations();
        firebase();

    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            updateUI(this, LoginActivity.class);
            this.finish();
        }

        if (v == btnResetPassword) {
            email = txtEmail.getText().toString();
            sendResetEmail();
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
    }

    private void sendResetEmail() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                makeToast("An E-Mail was Send To " + email);
                updateUI(this, LoginActivity.class);
                this.finish();
            } else {
                makeToast(Constants.TASK_IS_NOT_SUCCESSFUL);
            }
        });
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnLogin = (Button) findViewById(R.id.btnLoginReset);
        btnResetPassword = (Button) findViewById(R.id.btnResetPassword);

        //Listeners:
        btnLogin.setOnClickListener(this);
        btnResetPassword.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {
        txtEmail = (EditText) findViewById(R.id.txtEmailReset);
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