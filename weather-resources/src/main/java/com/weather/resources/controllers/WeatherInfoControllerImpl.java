package com.weather.resources.controllers;

import com.weather.models.WeatherInfoDto;
import com.weather.models.WeatherPredictionHelper;
import com.weather.resources.services.WeatherAppService;
import com.weather.resources.services.WeatherInfoService;
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
@RequestMapping(value = "/weatherinfo")
@RequiredArgsConstructor
@Tag(name = "Weather Info API")
public class WeatherInfoControllerImpl implements WeatherInfoController {

    private final WeatherInfoService weatherInfoService;

    @Operation(summary = "Fetch weather information from https://api.openweathermap.org")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherPredictionHelper.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Url", content = @Content)})
    @GetMapping
    public Flux<WeatherInfoDto> fetch(
            @Parameter(description = "Enter number of entries") @RequestParam(name = "size") Integer size,
            @Parameter(description = "Enter name of city") @RequestParam(name = "city") String city,
            @Parameter(description = "Enter page number") @RequestParam(name = "page") Integer pageNo) {
        return weatherInfoService.fetch(pageNo, size, city);
    }


}
