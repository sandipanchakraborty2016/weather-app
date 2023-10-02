package com.weather.commons.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBookAPIException(BusinessException businessException) {
        Map<String, String> errorMap = new HashMap<>();
        log.info("Business exception has occurred", businessException);
        errorMap.put("MESSAGE", businessException.getApiWeatherError().getErrorMessage());
        errorMap.put("STATUS", HttpStatus.BAD_REQUEST.toString());
        errorMap.put("CODE", businessException.getApiWeatherError().getErrorCode());
        errorMap.put("TIMESTAMP", businessException.getApiWeatherError().getTimestamp());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMap);
    }
}
