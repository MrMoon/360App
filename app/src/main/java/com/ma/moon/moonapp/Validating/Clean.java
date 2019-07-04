package com.ma.moon.moonapp.Validating;

import android.widget.EditText;
import android.widget.TextView;

public interface Clean {
    void registerCleanEditText(EditText emailTxt, EditText passwordTxt, EditText confirmPasswordTxt, EditText firstNameTxt, EditText lastNameTxt);

    void cleanEditText(EditText editText);

    void profileCleanTextView(TextView nameTxt, TextView emailTxt);

    void cleanTextView(TextView textView);

}
