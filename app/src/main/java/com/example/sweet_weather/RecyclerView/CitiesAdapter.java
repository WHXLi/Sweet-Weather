package com.example.sweet_weather.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweet_weather.R;

public class CitiesAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private String[] cities;

    public CitiesAdapter(String[] cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_unit, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.city.setText(cities[position]);
    }

    @Override
    public int getItemCount() {
        return cities.length;
    }
}
