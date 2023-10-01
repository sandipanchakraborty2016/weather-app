package com.weather.resources.services;

import com.weather.models.WeatherPredictionHelper;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WeatherAppService {

    Mono<List<WeatherPredictionHelper>> fetch(Integer size, String city);
}
