package com.example.dem;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    Context context;
    int layoutResource;
    ArrayList<QLSV> data;

    public Adapter(Context context, int layoutResource, ArrayList<QLSV> data) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.data = data;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutResource, viewGroup, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvten.setText(data.get(i).getTen());
        myViewHolder.tvmssv.setText(data.get(i).getMmsv());
        myViewHolder.imageView.setImageResource(data.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvten, tvmssv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvten = itemView.findViewById(R.id.tvten);
            tvmssv = itemView.findViewById(R.id.tvmssv);
            imageView = itemView.findViewById(R.id.image1);
        }
    }
}
