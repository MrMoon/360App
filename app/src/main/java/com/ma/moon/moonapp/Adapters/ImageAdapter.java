package com.ma.moon.moonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.House;
import com.ma.moon.moonapp.pojo.HouseName;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    //Objects and Variables:
    private Context context;
    private String[] txtList;
    private int[] imgList;
    private List<HouseName> houseList = new ArrayList<>();

    public ImageAdapter(Context context, String[] txtList, int[] imgList) {
        this.context = context;
        this.txtList = txtList;
        this.imgList = imgList;
    }

    public ImageAdapter(Context context, List<HouseName> houseList) {
        this.context = context;
        this.houseList = houseList;
    }


    @Override
    public int getCount() {
        return txtList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater =
                (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridviewandtxt, null);
            TextView textView = (TextView) gridView.findViewById(R.id.gridview_text);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.android_gridview_image);
            textView.setText(txtList[position]);
            imageView.setImageResource(imgList[position]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

}
