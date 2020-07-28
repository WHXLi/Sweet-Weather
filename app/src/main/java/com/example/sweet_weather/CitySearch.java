package com.example.sweet_weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.sweet_weather.databinding.CitySearchBinding;

public class CitySearch extends Activity {
    private CitySearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initCities();
        setContentView(view);
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
            button.setOnClickListener((v)->{
                Button currentButton = (Button) v;
                Intent intent = new Intent(CitySearch.this, Weather.class);
                intent.putExtra("cityName", currentButton.getText());
                startActivity(intent);
            });
        }
    }
}
