package com.ma.moon.moonapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.Interfaces.Components;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.Profile;

import java.util.*;

public class LoginActivity extends AppCompatActivity implements Basics, Components {
    //Objects and Variables:
    private Button btnRegister, btnLogin, btnSignInGoogle, btnResetPassword, btnExit;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText txtEmail, txtPassword;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 101;
    private GoogleSignInAccount account;
    private String fullname, email, id, emailLogin, passwordLogin;
    private GoogleSignInOptions gso;
    private HashMap<String, Object> loginHashMap;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //declarations for Components:
        declarations();

        //FireBase Objects:
        firebase();

        //Sign In:
        user = auth.getCurrentUser();
        account = GoogleSignIn.getLastSignedInAccount(this);

        //Setting up Google sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            updateUI(this, RegisterActivity.class);
            finish();
        }
        if (v == btnSignInGoogle) {
            signIn();
        }
        if (v == btnLogin) {
            progressDialog.setMessage(Constants.LOADING_PROGRESSBAR);
            progressDialog.show();
            editTextToString();
            signInEmailAndPassword(emailLogin, passwordLogin);
        }
        if (v == btnExit) {
            exit();
        }
        if (v == btnResetPassword) {
            updateUI(this, ResetPasswordActivity.class);
            finish();
        }
    }

    private void exit() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        LoginActivity.this.finish();
    }

    //Check If the user is sign In
    @Override
    protected void onStart() {
        super.onStart();
        user = auth.getCurrentUser();
        account = GoogleSignIn.getLastSignedInAccount(this);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //Regular Sign In with E-Mail and Password:
    private void signInEmailAndPassword(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                updateUI(this, DashboardActivity.class);
                LoginActivity.this.finish();
            } else {
                try {
                    makeToast(task.getException().getLocalizedMessage());
                    if (task.getException() instanceof FirebaseAuthEmailException) {
                        progressDialog.dismiss();
                        txtEmail.setError(Constants.EMAIL_ERROR);
                    } else {
                        progressDialog.dismiss();
                        txtPassword.setError(Constants.PASSWORD_ERROR);
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    System.out.println(e.toString());
                }
            }
        });
    }

    //Google Sign In API Result:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi:
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                writeData();
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                makeToast(Constants.ERROR);
            }
        }
    }

    //Get Info from Goolge and Save it to the Firebase:
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        addUser();
                    } else {
                        makeToast(Constants.ERROR + " " + Constants.ERROR_TRY_AGAIN);
                    }
                });
    }

    private void addUser() {
        updateUI(this, DashboardActivity.class);
        finish();
        signIn();
        userInfo();
    }


    private void userInfo() {
        firebase();
        if (user != null) {
            id = user.getUid();
            email = user.getEmail();
            fullname = user.getDisplayName();
        }
        writeData();
    }

    private void editTextToString() {
        emailLogin = txtEmail.getText().toString();
        passwordLogin = txtPassword.getText().toString();
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
    public void writeData() {
        firebase();
        reference = FirebaseDatabase.getInstance().getReference().child(Constants.DATABASE_PATH_USERS);
        String key = reference.child(Constants.DATABASE_PATH_USERS).push().getKey();
        Profile profile = new Profile(email, fullname, id);
        Map<String, Object> profileMap = profile.toMap(loginHashMap);
        reference.child(profile.getEmail().split("@")[0]).updateChildren(profileMap);
    }

    @Override
    public void readData() {

    }

    @Override
    public void firebase() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public void buttonsDeclaration() {

        //Buttons:
        btnRegister = (Button) findViewById(R.id.btnRegisterLogin);
        btnSignInGoogle = (Button) findViewById(R.id.btnSignInGoogle);
        btnLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnResetPassword = (Button) findViewById(R.id.btnResetPasswordLogin);
        btnExit = (Button) findViewById(R.id.btnExit);

        //Listener:
        btnRegister.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnResetPassword.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void textDeclaration() {
        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
    }

    @Override
    public void declarations() {
        textDeclaration();
        buttonsDeclaration();
        progressDialog = new ProgressDialog(this);
    }
}