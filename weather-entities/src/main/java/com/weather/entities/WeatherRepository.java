package com.weather.entities;


import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WeatherRepository extends ReactiveMongoRepository<WeatherInfo, String> {
    Flux<WeatherInfo> findWeatherInfoByCity(String city, Pageable size);
    Mono<WeatherInfo> findById(int id);

    Mono<WeatherInfo> deleteWeatherInfoById(int id);

}
