package com.weather.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Root {
    private String cod;
    private Integer message;
    private Integer cnt;
    private java.util.List<WeatherList> list = new ArrayList<>();
    private City city;
}
