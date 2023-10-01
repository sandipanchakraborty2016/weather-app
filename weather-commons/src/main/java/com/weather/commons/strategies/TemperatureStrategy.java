package com.weather.commons.strategies;

import com.weather.models.WeatherList;
import com.weather.models.WeatherPredictionHelper;
import org.springframework.stereotype.Component;

@Component
public class TemperatureStrategy implements ApiWeatherResponseStrategy {
    @Override
    public void process(WeatherList weather, WeatherPredictionHelper helper) {
        double temp = kelvinToCelsius(weather.getMain().getTemp());
        if (temp > 40) {
            helper.setMessage("Use sunscreen lotion");
        }
    }

    private static double kelvinToCelsius(double kelvin) {
        // Formula to convert Kelvin to Celsius: Celsius = Kelvin - 273.15
        return kelvin - 273.15;
    }
}
