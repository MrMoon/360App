package com.ma.moon.moonapp.CustomClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ma.moon.moonapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    //Objects and Variables:
    public TextView txtName;
    public ImageView imageView;

    //Super Constructor:
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.textViewName);
        imageView = (ImageView) itemView.findViewById(R.id.imageViewPreview);
    }
}
