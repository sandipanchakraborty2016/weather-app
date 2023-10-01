package com.weather.clients.services;

import com.weather.models.Root;
import reactor.core.publisher.Mono;
public interface ApiWeatherService {

    Mono<Root> fetchForecastForUpcomingDays(String city, Integer length);
}
