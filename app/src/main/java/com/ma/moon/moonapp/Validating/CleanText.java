package com.ma.moon.moonapp.Validating;

import android.widget.EditText;
import android.widget.TextView;

public class CleanText implements Clean {


    @Override
    public void registerCleanEditText(EditText emailTxt, EditText passwordTxt, EditText confirmPasswordTxt, EditText firstNameTxt, EditText lastNameTxt) {
        emailTxt.setText("");
        passwordTxt.setText("");
        confirmPasswordTxt.setText("");
        firstNameTxt.setText("");
        lastNameTxt.setText("");
    }

    @Override
    public void cleanEditText(EditText editText) {
        editText.setText("");
    }

    @Override
    public void profileCleanTextView(TextView nameTxt, TextView emailTxt) {
        nameTxt.setText("");
        emailTxt.setText("");
    }

    @Override
    public void cleanTextView(TextView textView) {
        textView.setText("");
    }
}
