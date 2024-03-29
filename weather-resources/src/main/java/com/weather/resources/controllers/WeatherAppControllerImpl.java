package com.weather.resources.controllers;

import com.weather.models.WeatherPredictionHelper;
import com.weather.resources.services.WeatherAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
@Tag(name = "Weather API")
public class WeatherAppControllerImpl implements WeatherAppController {

    private final WeatherAppService weatherAppService;

    @Operation(summary = "Fetch weather information from https://api.openweathermap.org")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherPredictionHelper.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Url", content = @Content)})
    @GetMapping
    public Flux<WeatherPredictionHelper> fetch(
            @Parameter(description = "Enter number of entries") @RequestParam(name = "size") Integer size,
            @Parameter(description = "Enter name of city") @RequestParam(name = "city") String city) {
        return weatherAppService.fetch(size, city);
    }
}

