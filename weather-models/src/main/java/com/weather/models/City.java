package com.weather.models;

import lombok.Builder;

@Builder
public record City(Integer id, String name, Coord coord, String country, Integer population, Integer timezone,
                   Integer sunrise, Integer sunset) {
}
