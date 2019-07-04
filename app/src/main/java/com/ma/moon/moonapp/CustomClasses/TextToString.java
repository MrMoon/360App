package com.ma.moon.moonapp.CustomClasses;

import android.widget.EditText;
import android.widget.TextView;
import com.ma.moon.moonapp.Interfaces.TxtToString;

public class TextToString implements TxtToString {
    @Override
    public void registerEditTextToString(EditText emailTxt, EditText passwordTxt, EditText confirmPasswordTxt, EditText nameTxt, String email, String password, String confirmPassword, String name) {
        email = emailTxt.getText().toString();
        password = passwordTxt.getText().toString();
        confirmPassword = confirmPasswordTxt.getText().toString();
        name = nameTxt.getText().toString();
    }

    @Override
    public void editTextToString(EditText editText, String s) {
        s = editText.getText().toString();
    }

    @Override
    public void profileTextViewToString(TextView emailTxt, TextView nameTxt, String email, String name) {
        email = emailTxt.getText().toString();
        name = nameTxt.getText().toString();
    }

    @Override
    public void textViewToString(TextView textView, String s) {
        s = textView.getText().toString();
    }
}
