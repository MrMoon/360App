package com.ma.moon.moonapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ma.moon.moonapp.R;

public class ViewPagerAdapterHouses extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] imgs = {R.drawable.onethree,R.drawable.twothree,R.drawable.threethree,R.drawable.fourthree,R.drawable.fivethree,R.drawable.sixthree,R.drawable.seventhree};

    public ViewPagerAdapterHouses(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(imgs[position]);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager pager = (ViewPager) container;
        View view = (View) object;
        pager.removeView(view);
    }
}
