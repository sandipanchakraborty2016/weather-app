package com.weather.resources.controllers;

import com.weather.resources.services.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherAppControllerImplTest {

    @MockBean
    private WeatherAppService service;

    @Autowired
    private WebTestClient webTestClient;

//    @Test
//    @DisplayName("GET /weather/london/15 - Success")
//    void success_fetchService() throws Exception {
//        this.webTestClient
//                .get()
//                .uri("/weather/london/15")
//                .exchange()
//                .expectStatus()
//                .isOk();
//    }


}