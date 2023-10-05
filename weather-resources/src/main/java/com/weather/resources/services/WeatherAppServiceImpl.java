package com.weather.resources.services;

import com.weather.clients.services.ApiWeatherService;
import com.weather.commons.validators.ApiWeatherValidator;
import com.weather.models.Root;
import com.weather.models.WeatherPredictionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherAppServiceImpl implements WeatherAppService {

    private final ApiWeatherService apiWeatherService;

    private final ApiWeatherProcessor apiWeatherProcessor;

    private final ApiWeatherValidator validator;

    @Override
    @Cacheable("weather")
    public Flux<WeatherPredictionHelper> fetch(Integer size, String city) {
        return apiWeatherService.fetchForecastForUpcomingDays(city, size).cache()
                .map(this::apply).flatMapMany(Flux::fromIterable).retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));
    }

    private List<WeatherPredictionHelper> apply(Root body) {

        Errors errors = new BeanPropertyBindingResult(
                body,
                Root.class.getName());
        validator.validate(body, errors);

        return apiWeatherProcessor.generate(body);
    }
}
