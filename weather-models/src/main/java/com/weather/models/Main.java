package com.weather.models;

import lombok.Data;

@Data
public class Main {
    private Double temp;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer pressure;
    private Integer seaLevel;
    private Integer grndLevel;
    private Integer humidity;
    private Integer tempKf;
}
