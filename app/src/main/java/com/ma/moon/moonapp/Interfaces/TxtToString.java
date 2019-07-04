package com.ma.moon.moonapp.Interfaces;

import android.widget.EditText;
import android.widget.TextView;

public interface TxtToString {
    void registerEditTextToString(EditText emailTxt, EditText passwordTxt, EditText confirmPasswordTxt, EditText nameTxt,
                                  String email, String password, String confirmPassword, String name);

    void editTextToString(EditText editText, String s);

    void profileTextViewToString(TextView emailTxt, TextView nameTxt, String email, String name);

    void textViewToString(TextView textView, String s);
}
