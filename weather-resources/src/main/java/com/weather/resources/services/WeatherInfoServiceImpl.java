package com.weather.resources.services;

import com.weather.entities.WeatherRepository;
import com.weather.models.WeatherInfoDto;
import com.weather.resources.mappers.WeatherInfoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherInfoServiceImpl implements WeatherInfoService {

    private final WeatherRepository weatherRepository;

    private final WeatherInfoConverter weatherInfoConverter;

    @Override
    public Flux<WeatherInfoDto> fetch(Integer page, Integer size, String city) {
        return weatherRepository
                .findWeatherInfoByCity(city, PageRequest.of(page, size))
                .map(weatherInfoConverter::convertToDto);
    }

    @Override
    public Mono<WeatherInfoDto> saveOrUpdate(WeatherInfoDto weatherInfoDto) {
        return weatherRepository
                .save(weatherInfoConverter.convertToEntity(weatherInfoDto))
                .map(weatherInfoConverter::convertToDto);
    }

    @Override
    public Mono<WeatherInfoDto> deleteById(Integer id) {
        return weatherRepository.deleteWeatherInfoById(id).map(weatherInfoConverter::convertToDto);
    }
}
