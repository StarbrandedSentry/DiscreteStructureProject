package com.iibcsad.recursion.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iibcsad.recursion.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DeveloperInfoAdapter extends RecyclerView.Adapter<DeveloperInfoAdapter.DeveloperViewHolder> {
    private ArrayList<String> names, ages, images;

    public DeveloperInfoAdapter(ArrayList<String> names, ArrayList<String> ages, ArrayList<String> images)
    {
        this.names = names;
        this.ages = ages;
        this.images = images;
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_developers, viewGroup, false);
        return new DeveloperViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder developerHolder, int i) {
        developerHolder.name.setText(names.get(i));
        developerHolder.age.setText(ages.get(i));

        Picasso.get()
                .load(Integer.parseInt(images.get(i)))
                .fit().centerCrop()
                .into(developerHolder.image);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class DeveloperViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;
        public TextView age;
        public ImageView image;
        public DeveloperViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.l_developer_name);
            age = itemView.findViewById(R.id.l_developer_age);
            image = itemView.findViewById(R.id.l_developer_image);
        }
    }
}
