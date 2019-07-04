package com.ma.moon.moonapp.Validating;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class BooleanChecking implements Checking {
    //Variables and Objects:
    private AllErrors errors;

    @Override
    public boolean isNull(Object o) {
        return o != null;
    }

    @Override
    public boolean isClicked(Button button) {
        return button != null;
    }

    @Override
    public boolean emptyInput(EditText editText, String s) {
        if (TextUtils.isEmpty(s)) {
            errors.editTextError(editText);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEmailValid(String s) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches();
    }

    @Override
    public boolean isValidMobile(String s) {
        return android.util.Patterns.PHONE.matcher(s).matches();
    }
}
