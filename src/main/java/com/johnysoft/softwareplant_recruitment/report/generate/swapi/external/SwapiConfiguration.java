package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class SwapiConfiguration {
    private static final String SWAPI_BASE_URL = "https://swapi.co/api/";

    @Bean
    WebClient swapiWebClient() {
        return WebClient.builder()
                .baseUrl(SWAPI_BASE_URL)
                .build();
    }
}
