package com.ma.moon.moonapp.Validating;

import android.widget.Button;
import android.widget.EditText;

public interface Checking {
    boolean isNull(Object o);

    boolean isClicked(Button button);

    boolean emptyInput(EditText editText, String s);

    boolean isEmailValid(String s);

    boolean isValidMobile(String s);

}
