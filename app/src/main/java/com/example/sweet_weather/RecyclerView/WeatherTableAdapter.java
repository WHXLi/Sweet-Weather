package com.example.sweet_weather.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweet_weather.R;

public class WeatherTableAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private String[] days;
    private int[] statuses;
    private String[] temperatures;
    private int count = 7;

    public WeatherTableAdapter(String[] days, int[] statuses, String[] temperatures) {
        this.days = days;
        this.statuses = statuses;
        this.temperatures = temperatures;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_table, parent, false);
        setLayoutParams(parent,view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.day.setText(days[position]);
        holder.status.setImageResource(statuses[position]);
        holder.temperature.setText(temperatures[position]);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    private void setLayoutParams(ViewGroup parent, View view){
        int calculationsError = 1;
        int height = parent.getMeasuredHeight();
        int width = parent.getMeasuredWidth() / count + calculationsError;
        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
    }
}
