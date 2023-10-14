package com.weather.models;

import lombok.Data;

@Data
public class WeatherInfoDto {
    private String date;
    private String message;
    private String city;
}
