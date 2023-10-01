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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
@Tag(name = "Weather Controller API")
public class WeatherAppControllerImpl implements WeatherAppController{

    private final WeatherAppService weatherAppService;

    @Operation(summary = "Fetch weather information from https://api.openweathermap.org")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherPredictionHelper.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Url", content = @Content)})
    @GetMapping("/{city}/{size}")
    public Mono<List<WeatherPredictionHelper>> fetch(
            @Parameter(description = "Enter number of entries")  @PathVariable Integer size,
            @Parameter(description = "Enter name of city")  @PathVariable String city) {
        return weatherAppService.fetch(size, city);
    }
}

