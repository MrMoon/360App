package com.ma.moon.moonapp.Validating;

import android.widget.EditText;

public class AllErrors implements Errors {
    @Override
    public void editTextError(EditText editText) {
        editText.setError("Please Enter Required Data");
    }

    @Override
    public void editTextCustomError(EditText editText, String s) {
        editText.setError(s);
    }

    @Override
    public void editTextDoubleError(EditText editText1, EditText editText2, String s) {
        editText1.setError(s);
        editText2.setError(s);
    }

}
