package com.weather.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBookAPIException(BusinessException businessException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("MESSAGE", businessException.getApiWeatherError().getErrorMessage());
        errorMap.put("STATUS", HttpStatus.BAD_REQUEST.toString());
        errorMap.put("CODE",  businessException.getApiWeatherError().getErrorCode());
        errorMap.put("DATE", String.valueOf(businessException.getApiWeatherError().getTimestamp()));
        return ResponseEntity.ok(errorMap);
    }
}
