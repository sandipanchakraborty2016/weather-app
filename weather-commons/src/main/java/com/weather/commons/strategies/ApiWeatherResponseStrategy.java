package com.weather.commons.strategies;

import com.weather.models.WeatherList;
import com.weather.models.WeatherPredictionHelper;

public interface ApiWeatherResponseStrategy {

    void process(WeatherList weather, WeatherPredictionHelper helper);
}
