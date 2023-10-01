package com.weather.resources.services;

import com.weather.commons.strategies.RainOrThunderstormStrategy;
import com.weather.commons.strategies.TemperatureStrategy;
import com.weather.commons.strategies.WindStrategy;
import com.weather.models.Root;
import com.weather.models.WeatherList;
import com.weather.models.WeatherPredictionHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ApiWeatherProcessor {

    private final TemperatureStrategy temperatureStrategy;
    private final RainOrThunderstormStrategy rainOrThunderstormStrategy;
    private final WindStrategy windStrategy;

    public List<WeatherPredictionHelper> generate(Root root) {

        List<WeatherPredictionHelper> weatherForecastList = new ArrayList<>();
        List<WeatherList> list = root.getList();
        calculate(list, weatherForecastList);
        return weatherForecastList;
    }

    private void calculate(List<WeatherList> list, List<WeatherPredictionHelper> weatherForecastList) {
        list.forEach(weather -> {
            WeatherPredictionHelper helper = new WeatherPredictionHelper();
            helper.setDate(weather.getDtTxt());
            temperatureStrategy.process(weather, helper);
            rainOrThunderstormStrategy.process(weather, helper);
            windStrategy.process(weather, helper);
            weatherForecastList.add(helper);
        });
    }
}
