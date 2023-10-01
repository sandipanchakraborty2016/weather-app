package com.weather.resources.controllers;

import com.weather.models.WeatherPredictionHelper;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WeatherAppController {

    Mono<List<WeatherPredictionHelper>>  fetch(Integer size, String city);
}
