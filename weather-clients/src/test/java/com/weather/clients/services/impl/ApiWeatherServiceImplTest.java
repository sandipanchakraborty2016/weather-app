package com.weather.clients.services.impl;

import com.weather.clients.config.ApiClientConfiguration;
import com.weather.models.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiWeatherServiceImplTest {

    private ApiWeatherServiceImpl apiWeatherService;

    @BeforeEach
    void setUp() {
        apiWeatherService = new ApiWeatherServiceImpl(new ApiClientConfiguration().webClient());
    }
    @Test
    void fetchForecastForUpcomingDays() {
        Root london = apiWeatherService.fetchForecastForUpcomingDays( "London",3).block();
        assertThat(london).isNotNull();
    }
}