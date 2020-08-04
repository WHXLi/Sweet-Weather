package com.example.sweet_weather;

public enum CityName {
    INSTANCE;
    private String cityName;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
