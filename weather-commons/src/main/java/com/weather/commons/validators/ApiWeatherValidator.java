package com.weather.commons.validators;

import com.weather.commons.exceptions.ApiWeatherError;
import com.weather.commons.exceptions.BusinessException;
import com.weather.models.Root;
import com.weather.models.WeatherList;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class ApiWeatherValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Root.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Root request = (Root) target;
        Optional<List<WeatherList>> list =
                Optional.ofNullable(request.getList());
        if (list.isEmpty()) {
            throw new BusinessException(ApiWeatherError.builder().errorCode("1001").build());
        }
    }
}