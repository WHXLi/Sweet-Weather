package com.example.sweet_weather;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sweet_weather.databinding.WeatherBinding;


public class Weather extends AppCompatActivity {
    private WeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceCheck(savedInstanceState);
        binding = WeatherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initTextViews();
        initListener();
        setContentView(view);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logLifeStage("onRestoreInstanceState", "смена ориентации экрана");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logLifeStage("onRestart", "перезапуск активити");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logLifeStage("onStop", "остановка предыдущей активити");
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
        binding.cityName.setText(CityName.INSTANCE.getCityName());
    }

    private void initListener() {
        binding.citySearchButton.setOnClickListener((v) ->
                startActivity(new Intent(Weather.this, CitySearch.class)));
    }

    private void instanceCheck(Bundle savedInstanceState) {
        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск";
        } else instanceState = "Повторный запуск";
        Toast.makeText(getApplicationContext(),
                instanceState + " - активити создано",
                Toast.LENGTH_SHORT).show();
    }

    private void logLifeStage(String tag, String text ){
        Toast.makeText(getApplicationContext(),
                tag + " - " + text,
                Toast.LENGTH_SHORT).show();
        Log.d(tag, text);
    }
}