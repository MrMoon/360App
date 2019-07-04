package com.ma.moon.moonapp.Interfaces;

import android.content.Context;
import android.view.View;

/**
 * For Basic Operations in our case:
 *  -Toasts
 *  -Update Activities
 *  -Reading and Writing Data
 *  -Firebase Objects and Operations
 */
public interface Basics extends View.OnClickListener {
    void makeToast(String message);
    void updateUI(Context context ,Class<?> aClass);
    void writeData();
    void readData();
    void firebase();
}
