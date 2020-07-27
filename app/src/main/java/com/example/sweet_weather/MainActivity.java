package com.example.sweet_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.sweet_weather.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        initTextViews();
    }

    private void initTextViews() {
        TextView[] textTemperatures = new TextView[]
                {
                        binding.textTemperatureNow,
                        binding.textTemperatures1,
                        binding.textTemperatures2,
                        binding.textTemperatures3,
                        binding.textTemperatures4,
                        binding.textTemperatures5,
                        binding.textTemperatures6,
                        binding.textTemperatures7
                };
        TextView[] textDays = new TextView[]
                {
                        binding.textDay1,
                        binding.textDay2,
                        binding.textDay3,
                        binding.textDay4,
                        binding.textDay5,
                        binding.textDay6,
                        binding.textDay7
                };
        TextView[] textStatuses = new TextView[]
                {
                        binding.textStatus,
                };
        TextView[] textCities = new TextView[]
                {
                        binding.textCity
                };
        String[] days = getResources().getStringArray(R.array.days);
        String[] temperatures = getResources().getStringArray(R.array.temperatures);
        String[] statuses = getResources().getStringArray(R.array.statuses);
        String[] cities = getResources().getStringArray(R.array.cities);
        setTextViews(textDays, days);
        setTextViews(textTemperatures, temperatures);
        setTextViews(textStatuses, statuses);
        setTextViews(textCities, cities);
    }

    private void setTextViews(TextView[] target, String[] values) {
        for (int i = 0; i < target.length; i++) {
            target[i].setText(values[i]);
        }
    }
}