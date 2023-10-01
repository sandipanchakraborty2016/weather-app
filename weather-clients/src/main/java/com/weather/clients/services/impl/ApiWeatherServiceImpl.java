package com.weather.clients.services.impl;

import com.weather.clients.services.ApiWeatherService;
import com.weather.commons.exceptions.ApiWeatherError;
import com.weather.commons.exceptions.BusinessException;
import com.weather.models.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.weather.clients.config.ConfigConstants.API_WEATHER_APP_ID;
import static com.weather.clients.config.ConfigConstants.FORECAST_BASE_URL;

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
                .onErrorResume(BusinessException.class,
                        ex -> Mono.error(new BusinessException(ApiWeatherError.builder().errorCode("1001").build())));
    }
}
