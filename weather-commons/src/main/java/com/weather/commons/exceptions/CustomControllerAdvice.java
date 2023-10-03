package com.weather.commons.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

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


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleBookAPIException(RuntimeException runtimeException) {
        Map<String, String> errorMap = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("Unknown exception has occurred", runtimeException);
        errorMap.put("MESSAGE", "UNKNOWN_EXCEPTION_PLEASE CONTACT CUSTOMER CARE.");
        errorMap.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorMap.put("TIMESTAMP", dateTimeFormatter.format(now()));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMap);
    }
}
