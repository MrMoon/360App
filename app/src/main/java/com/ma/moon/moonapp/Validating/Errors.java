package com.ma.moon.moonapp.Validating;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public interface Errors {
    void editTextError(EditText editText);

    void editTextCustomError(EditText editText, String s);

    void editTextDoubleError(EditText editText1, EditText editText2, String s);

}
