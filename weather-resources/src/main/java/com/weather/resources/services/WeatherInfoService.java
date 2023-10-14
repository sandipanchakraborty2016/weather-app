package com.weather.resources.services;

import com.weather.models.WeatherInfoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeatherInfoService {
    Flux<WeatherInfoDto> fetch(Integer page, Integer size, String city);

    Mono<WeatherInfoDto> saveOrUpdate(WeatherInfoDto weatherInfoDto);

    Mono<WeatherInfoDto> deleteById(Integer id);


}
