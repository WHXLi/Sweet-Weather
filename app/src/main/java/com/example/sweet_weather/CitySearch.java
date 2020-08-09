package com.example.sweet_weather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.sweet_weather.EventBus.CityName_Event;
import com.example.sweet_weather.EventBus.EventBus;
import com.example.sweet_weather.RecyclerView.CitiesAdapter;
import com.example.sweet_weather.RecyclerView.MyViewHolder;
import com.example.sweet_weather.RecyclerView.TouchListener;

public class CitySearch extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_search, container, false);
        initCities(view);
        return view;
    }

    private void initCities(View view) {
        String[] cities = getResources().getStringArray(R.array.cities);
        RecyclerView cities_list = view.findViewById(R.id.cities_list);
        LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Adapter<MyViewHolder> adapter_cities = new CitiesAdapter(cities);
        cities_list.setLayoutManager(layoutManager);
        cities_list.setAdapter(adapter_cities);
        initClickListener(cities_list);
    }

    private void initClickListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(
                new TouchListener(getActivity(), recyclerView, new TouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        transaction((TextView) view);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                }));
    }

    private void transaction(TextView view) {
        Weather weather = new Weather();
        EventBus.getBus().register(weather);
        EventBus.getBus().post(new CityName_Event(view.getText().toString()));
        assert getFragmentManager() != null;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container2, weather)
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, weather)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
