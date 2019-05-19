package com.example.dem;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    Context context;
    int layoutResource;
    ArrayList<PhepTinh> data;

    public Adapter(Context context, int layoutResource, ArrayList<PhepTinh> data) {
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
        myViewHolder.txtA.setText(data.get(i).getSoA());
        myViewHolder.txtB.setText(data.get(i).getSoB());
        myViewHolder.txtPT.setText(data.get(i).getPt());
        myViewHolder.txtKQ.setText(data.get(i).getKq());
        myViewHolder.imageView.setImageResource(data.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtA, txtB, txtPT, txtKQ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtA = itemView.findViewById(R.id.tvA);
            txtB = itemView.findViewById(R.id.tvB);
            txtPT = itemView.findViewById(R.id.tvPT);
            txtKQ = itemView.findViewById(R.id.tvKQ);
            imageView = itemView.findViewById(R.id.image1);
        }
    }
}
