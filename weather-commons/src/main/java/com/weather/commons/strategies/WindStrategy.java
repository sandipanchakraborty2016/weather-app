package com.weather.commons.strategies;

import com.weather.models.WeatherList;
import com.weather.models.WeatherPredictionHelper;
import org.springframework.stereotype.Component;

@Component
public class WindStrategy implements ApiWeatherResponseStrategy {
    @Override
    public void process(WeatherList weather, WeatherPredictionHelper helper) {
        Double speed = weather.getWind().getSpeed();
        if (speed > 10) {
            helper.setMessage("It’s too windy, watch out!”");
        }
    }
}
