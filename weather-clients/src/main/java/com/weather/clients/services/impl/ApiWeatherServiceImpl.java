package com.weather.clients.services.impl;

import com.weather.clients.services.ApiWeatherService;
import com.weather.commons.exceptions.ApiWeatherError;
import com.weather.commons.exceptions.BusinessException;
import com.weather.models.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static com.weather.clients.config.ConfigConstants.API_WEATHER_APP_ID;
import static com.weather.clients.config.ConfigConstants.FORECAST_BASE_URL;
import static com.weather.commons.constants.ErrorConstants.BUSINESS_EXCEPTION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class ApiWeatherServiceImpl implements ApiWeatherService {

    private final WebClient webClient;

    @Override
    public Mono<Root> fetchForecastForUpcomingDays(String city, Integer length) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path(FORECAST_BASE_URL)
                        .queryParam("q", city)
//                        .queryParam("appid", "API_WEATHER_APP_ID")
                        .queryParam("appid", API_WEATHER_APP_ID)
                        .queryParam("cnt", length)
                        .build())
                .retrieve()
                .bodyToMono(Root.class)
                .cache(Duration.ofMinutes(60))
                .defaultIfEmpty(new Root())
                .onErrorResume(RuntimeException.class,
                        ex -> Mono.error(new BusinessException(
                                ApiWeatherError
                                        .builder()
                                        .errorName(BUSINESS_EXCEPTION.getErrorName())
                                        .errorCode(String.valueOf(BUSINESS_EXCEPTION.getErrorCode()))
                                        .errorMessage(BUSINESS_EXCEPTION.getErrorMessage())
                                        .build())));
    }
}
