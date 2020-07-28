package com.example.sweet_weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweet_weather.databinding.WeatherBinding;

import java.util.Objects;


public class Weather extends AppCompatActivity {
    private WeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WeatherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initTextViews();
        initListener();
        setContentView(view);
    }

    private void initTextViews() {
        TextView[] textTemperatures = new TextView[]
                {
                        binding.cityTemperature,
                        binding.temperature1,
                        binding.temperature2,
                        binding.temperature3,
                        binding.temperature4,
                        binding.temperature5,
                        binding.temperature6,
                        binding.temperature7
                };
        TextView[] textDays = new TextView[]
                {
                        binding.day1,
                        binding.day2,
                        binding.day3,
                        binding.day4,
                        binding.day5,
                        binding.day6,
                        binding.day7
                };
        TextView[] textStatuses = new TextView[]
                {
                        binding.cityStatus
                };
        String[] days = getResources().getStringArray(R.array.days);
        String[] temperatures = getResources().getStringArray(R.array.temperatures);
        String[] statuses = getResources().getStringArray(R.array.statuses);
        setTextViews(textDays, days);
        setTextViews(textTemperatures, temperatures);
        setTextViews(textStatuses, statuses);
        setCityName();
    }

    private void setTextViews(TextView[] target, String[] values) {
        for (int i = 0; i < target.length; i++) {
            target[i].setText(values[i]);
        }
    }

    private void setCityName(){
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        binding.cityName.setText(Objects.requireNonNull(bundle.get("cityName")).toString());
    }

    private void initListener() {
        binding.citySearchButton.setOnClickListener((v) ->
                startActivity(new Intent(Weather.this, CitySearch.class)));
    }
}