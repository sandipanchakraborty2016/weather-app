package com.weather.resources.controllers;

import com.weather.models.WeatherPredictionHelper;
import reactor.core.publisher.Flux;

public interface WeatherAppController {

    Flux<WeatherPredictionHelper> fetch(Integer size, String city);
}
