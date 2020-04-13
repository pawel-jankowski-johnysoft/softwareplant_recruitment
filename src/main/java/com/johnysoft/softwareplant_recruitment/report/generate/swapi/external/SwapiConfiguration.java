package com.johnysoft.softwareplant_recruitment.report.generate.swapi.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class SwapiConfiguration {
    protected static final String BASE_URL = "${swapi.base_url}";

    @Bean
    WebClient swapiWebClient(@Value(BASE_URL) String swapiBaseUrl) {
        return WebClient.builder()
                .baseUrl(swapiBaseUrl)
                .build();
    }
}
