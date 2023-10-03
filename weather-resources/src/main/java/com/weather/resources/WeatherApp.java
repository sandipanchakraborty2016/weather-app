package com.weather.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.weather"})
@EnableCaching
public class WeatherApp {
    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }
}