package com.example.sweet_weather;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.sweet_weather.EventBus.CityName_Event;
import com.example.sweet_weather.RecyclerView.MyViewHolder;
import com.example.sweet_weather.RecyclerView.WeatherTableAdapter;
import com.example.sweet_weather.databinding.WeatherBinding;
import com.squareup.otto.Subscribe;

import java.util.Objects;

public class Weather extends Fragment {
    private WeatherBinding binding;
    private String cityName;

    public Weather(){}

    @Subscribe
    @SuppressWarnings("unused")
    public void onEvent(CityName_Event event) {
        cityName = event.cityName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WeatherBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initHeader();
        initWeatherTable(view);
        initClickListeners();
        initLandscapeFragment();
        return view;
    }

    private void initHeader() {
        String[] statuses = getResources().getStringArray(R.array.statuses);
        String[] temperatures = getResources().getStringArray(R.array.temperatures);
        int status = (int) (Math.random() * statuses.length);
        int temperature = (int) (Math.random() * temperatures.length);
        binding.cityName.setText(cityName);
        binding.cityStatus.setText(statuses[status]);
        binding.cityTemperature.setText(temperatures[temperature]);
    }

    private void initWeatherTable(View view) {
        String[] days = getResources().getStringArray(R.array.days);
        int[] statuses = {
                R.mipmap.sun,
                R.mipmap.storm,
                R.mipmap.cloud,
                R.mipmap.rain,
                R.mipmap.storm,
                R.mipmap.cloud,
                R.mipmap.rain
        };
        String[] temperatures = getResources().getStringArray(R.array.temperatures);
        RecyclerView weatherTable = view.findViewById(R.id.weather_table);
        LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        Adapter<MyViewHolder> weatherTableAdapter = new WeatherTableAdapter(days, statuses, temperatures);
        weatherTable.setLayoutManager(layoutManager);
        weatherTable.setAdapter(weatherTableAdapter);
    }

    private void initLandscapeFragment() {
        if (cityName != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Objects.requireNonNull(getActivity()).findViewById(R.id.container2).setVisibility(View.VISIBLE);
        }
    }

    private void initClickListeners() {
        binding.cityInfoButton.setOnClickListener(v -> {
            Uri uri = Uri.parse(getResources().getString(R.string.cityInfo_link) + binding.cityName.getText().toString());
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            assert binding.citySearchButton != null;
            binding.citySearchButton.setOnClickListener(v ->{
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new CitySearch())
                        .addToBackStack(null)
                        .commit();
            });
        }
    }
}