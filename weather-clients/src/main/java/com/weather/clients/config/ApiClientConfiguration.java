package com.weather.clients.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class ApiClientConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(ConfigConstants.API_WEATHER_BASE_URL).build();
    }

}
