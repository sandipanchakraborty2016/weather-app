package com.weather.resources.mappers;

import com.weather.entities.WeatherInfo;
import com.weather.models.WeatherInfoDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherInfoConverter {

    public WeatherInfoDto convertToDto(WeatherInfo info) {
        WeatherInfoDto dto = new WeatherInfoDto();
        dto.setCity(info.getCity());
        dto.setDate(info.getDate());
        dto.setMessage(info.getMessage());
        return dto;
    }

    public WeatherInfo convertToEntity(WeatherInfoDto infoDto) {
        WeatherInfo info = new WeatherInfo();
        info.setCity(infoDto.getCity());
        info.setMessage(infoDto.getMessage());
        info.setDate(infoDto.getDate());
        return info;
    }

}
