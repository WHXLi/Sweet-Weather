package com.example.sweet_weather.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweet_weather.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    protected TextView day;
    protected TextView city;
    protected ImageView status;
    protected TextView temperature;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        city = itemView.findViewById(R.id.city);
        day = itemView.findViewById(R.id.day);
        status = itemView.findViewById(R.id.status);
        temperature = itemView.findViewById(R.id.temperature);
    }
}
