package com.ma.moon.moonapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.Profile;

import java.util.*;

public class RegisterActivity extends AppCompatActivity implements Basics,Components {

    //Variables and Objects:
    private EditText txtFirstName, txtLastName, txtEmail, txtPassword, txtConfirmPassword;
    private Button btnRegister, btnLogin;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private String firstName, lastName, email, password, confirmedPassword, fullName, key;
    private Profile registeredProfile, profile;
    private List<Profile> profileList;
    private HashMap<String, Object> registerHashMap;
    private Map<String, Object> profileMap;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Objects:
        profileList = new ArrayList<Profile>();
        registeredProfile = new Profile();
        registerHashMap = new HashMap<>();
        progressDialog = new ProgressDialog(this);

        declarations();
        firebase();
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            editTexttoString();
            if (vaildInput()) {
                progressDialog.setMessage(Constants.LOADING_PROGRESSBAR);
                progressDialog.show();
                registerUser();
            }
        }
        if (v == btnLogin) {
            updateUI(this,LoginActivity.class);
            RegisterActivity.this.finish();
        }
    }

    //Registering the user:
    private void registerUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        //Firebase creating a user using E-mail and Password:
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                if (task.isSuccessful() && currentUser.isEmailVerified()) {
                    writeData();
                    progressDialog.dismiss();
                } else {
                   makeToast(Constants.TASK_IS_NOT_SUCCESSFUL);
                }
            }
        });
    }

    //Checking If the Input is valid:
    private boolean vaildInput() {
        if (TextUtils.isEmpty(email)) {
            txtEmail.setError(Constants.REQUIERED_INPUT);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            txtPassword.setError(Constants.REQUIERED_INPUT);
            return false;
        }
        if (TextUtils.isEmpty(firstName)) {
            txtFirstName.setError(Constants.REQUIERED_INPUT);
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            txtLastName.setError(Constants.REQUIERED_INPUT);
            return false;
        }

        if (TextUtils.isEmpty(confirmedPassword)) {
            txtConfirmPassword.setError(Constants.REQUIERED_INPUT);
            return false;
        }

        if (password.length() < 6) {
            txtPassword.setError(Constants.VALID_PASSWORD);
            return false;
        }

        if (!isEmailValid(email)) {
            txtEmail.setError(Constants.VALID_EMAIL);
            return false;
        }

        if (!password.equals(confirmedPassword)) {
            txtPassword.setError(Constants.VALID_CONFIRM_PASSWORD);
            txtConfirmPassword.setError(Constants.VALID_CONFIRM_PASSWORD);
            return false;
        }
        return true;
    }

    //Validating E-Mail pattern:
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void emailVerify() {
        //Firebase:
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //Sending An E-Mail to the Registered User:
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(task -> {
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null && task.isSuccessful()) {
                    Intent dashboardIntent = new Intent(this, DashboardActivity.class);
                    writeData();
                    startActivity(dashboardIntent);
                    RegisterActivity.this.finish();
                } else {
                    makeToast(Constants.EMAIL_VERIFICATION);
                }
            });
        }
    }


    private void editTexttoString() {
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        confirmedPassword = txtConfirmPassword.getText().toString();
        firstName = txtFirstName.getText().toString();
        lastName = txtLastName.getText().toString();
        fullName = firstName + " " + lastName;
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {
        Intent intent = new Intent(getApplicationContext(),aClass);
        startActivity(intent);
    }

    @Override
    public void writeData() {
        key = myRef.child(Constants.DATABASE_PATH_USERS).push().getKey();
        profile = new Profile(email, fullName, key);
        profileMap = profile.toMap(registerHashMap);
        myRef.child(profile.getId()).updateChildren(profileMap);
    }

    @Override
    public void readData() {

    }

    @Override
    public void firebase() {
        //Firebase Objects:
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constants.DATABASE_PATH_USERS);
        user = auth.getCurrentUser();
    }

    @Override
    public void buttonsDeclaration() {
        //Buttons:
        btnRegister = (Button) findViewById(R.id.btnRegisterRegister);
        btnLogin = (Button) findViewById(R.id.btnLoginRegister);

        //Listeners:
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {
        //Texts:
        txtFirstName = (EditText) findViewById(R.id.txtFristNameRegister);
        txtLastName = (EditText) findViewById(R.id.txtLastNameRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPasswordRegister);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
    }

    @Override
    public void declarations() {
        textDeclaration();
        buttonsDeclaration();
    }
}