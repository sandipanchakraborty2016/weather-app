package com.weather.clients.services.impl;

import com.weather.clients.config.ApiClientConfiguration;
import com.weather.commons.constants.ErrorConstants;
import com.weather.commons.exceptions.BusinessException;
import com.weather.models.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiWeatherServiceImplTest {

    private ApiWeatherServiceImpl apiWeatherService;

    @BeforeEach
    void setUp() {
        apiWeatherService = new ApiWeatherServiceImpl(new ApiClientConfiguration().webClient());
    }
    @Test
    void valid_fetchForecastForUpcomingDays() {
        Root london = apiWeatherService.fetchForecastForUpcomingDays( "London",3).block();
        assertThat(london).isNotNull();
    }

    @Test
    void fetchForecastForUpcomingDays() {
        BusinessException correct = assertThrows(BusinessException.class, ()->apiWeatherService.fetchForecastForUpcomingDays( "London123",3).block());
        assertEquals(ErrorConstants.BUSINESS_EXCEPTION.getErrorMessage(), correct.getApiWeatherError().getErrorMessage());
    }
}