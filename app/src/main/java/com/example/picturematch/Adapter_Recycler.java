package com.example.picturematch;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Recycler extends RecyclerView.Adapter<Adapter_Recycler.ViewHolder> {

    Activity activity;
    List<Integer> image;
    Recycler_Clicklistner listner;

    public Adapter_Recycler(Activity activity, List<Integer> image, Recycler_Clicklistner listner) {

        this.activity = activity;
        this.image = image;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = (LayoutInflater.from(activity).inflate(R.layout.recycler_item, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.img.setImageResource(image.get(position));
        holder.cover.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 16; i++) {
                    holder.cover.setVisibility(View.VISIBLE);
                }
            }
        }, 5000);

        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(image, holder.cover, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img, cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            cover = itemView.findViewById(R.id.cover);
        }
    }
}
