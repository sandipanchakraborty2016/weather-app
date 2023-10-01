package com.weather.commons.strategies;

import com.weather.models.WeatherList;
import com.weather.models.WeatherPredictionHelper;
import org.springframework.stereotype.Component;

@Component
public class RainOrThunderstormStrategy implements ApiWeatherResponseStrategy {
    @Override
    public void process(WeatherList weather, WeatherPredictionHelper helper) {
        String info = weather.getWeather().get(0).getMain();
        if (info.equals("Rain")) {
            helper.setMessage("Carry umbrella");
        } else if (info.equals("Thunderstorms")) {
            helper.setMessage("Don’t step out! A Storm is brewing!”");
        }
    }
}
