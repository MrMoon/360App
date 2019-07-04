package com.ma.moon.moonapp.CustomClasses;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.ma.moon.moonapp.Interfaces.Passwords;

public class ResetPassword implements Passwords {

    @Override
    public void sendResetEmail(Context context, String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(context,"E-Mail was sent to " + email,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
