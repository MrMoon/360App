package com.ma.moon.moonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.HouseName;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<HouseName> houses;

    public CustomAdapter(Context c, ArrayList<HouseName> houses) {
        this.c = c;
        this.houses = houses;
    }

    @Override
    public int getCount() {
        return houses.size();
    }

    @Override
    public Object getItem(int position) {
        return houses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.gridviewandtxt,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.gridview_text);

        final HouseName s= (HouseName) this.getItem(position);

        nameTxt.setText(s.getName());

        //ONITECLICK
        convertView.setOnClickListener(v -> Toast.makeText(c,s.getName(),Toast.LENGTH_SHORT).show());

        return convertView;
    }
}
