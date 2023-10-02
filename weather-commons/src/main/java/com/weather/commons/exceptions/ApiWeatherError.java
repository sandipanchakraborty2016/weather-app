package com.weather.commons.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;


@Builder
@Getter
public class ApiWeatherError {

    private String errorName;
    private String errorCode;
    private String errorMessage;
    private String timestamp;

    public String getTimestamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(now());
    }
}
