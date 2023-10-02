package com.weather.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SystemException extends RuntimeException {

    private ApiWeatherError apiWeatherError;
}
