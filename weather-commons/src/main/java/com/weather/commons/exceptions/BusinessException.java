package com.weather.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class BusinessException extends RuntimeException {

    private ApiWeatherError apiWeatherError;

}
