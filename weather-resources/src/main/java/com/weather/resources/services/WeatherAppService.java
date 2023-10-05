package com.weather.resources.services;

import com.weather.models.WeatherPredictionHelper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WeatherAppService {

    Flux<WeatherPredictionHelper> fetch(Integer size, String city);
}
