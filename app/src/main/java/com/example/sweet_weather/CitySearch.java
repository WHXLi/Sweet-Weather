package com.example.sweet_weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sweet_weather.databinding.CitySearchBinding;

public class CitySearch extends Activity {
    private CitySearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceCheck(savedInstanceState);
        binding = CitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initCities();
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

    private void initCities() {
        String[] cities = getResources().getStringArray(R.array.cities);
        Button[] buttons = new Button[]{
                binding.city1Link,
                binding.city2Link,
                binding.city3Link,
                binding.city4Link,
                binding.city5Link,
                binding.city6Link,
                binding.city7Link,
                binding.city8Link,
                binding.city9Link,
                binding.city10Link
        };
        setCitiesNames(buttons, cities);
        initListeners(buttons);
    }

    private void setCitiesNames(Button[] target, String[] values) {
        for (int i = 0; i < target.length; i++) {
            target[i].setText(values[i]);
        }
    }

    private void initListeners(Button[] buttons) {
        for (Button button : buttons) {
            button.setOnClickListener((v) -> {
                Button currentButton = (Button) v;
                Intent intent = new Intent(CitySearch.this, Weather.class);
                intent.putExtra("cityName", currentButton.getText().toString());
                startActivity(intent);
            });
        }
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

    private void logLifeStage(String tag, String text) {
        Toast.makeText(getApplicationContext(),
                tag + " - " + text,
                Toast.LENGTH_SHORT).show();
        Log.d(tag, text);
    }
}
