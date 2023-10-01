package com.weather.models;

import lombok.Builder;

@Builder
public record Coord(Double lat, Double lon) {
}
