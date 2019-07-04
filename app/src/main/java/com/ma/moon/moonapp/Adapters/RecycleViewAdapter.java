package com.ma.moon.moonapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.ma.moon.moonapp.CustomClasses.ViewHolder;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.pojo.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    //Objects and Variables:
    private Context context;
    private List<Upload> uploadList;

    //Constructors:
    public RecycleViewAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_images, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Upload upload = uploadList.get(i);
        viewHolder.txtName.setText(upload.getImgName());
        Glide.with(context).load(upload.getImgURL()).into(viewHolder.imageView);
        //Picasso.get().load(upload.getImgURL()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

}
